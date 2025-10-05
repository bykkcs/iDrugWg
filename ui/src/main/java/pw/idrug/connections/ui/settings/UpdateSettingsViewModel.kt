package pw.idrug.connections.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.data.UpdateMeta
import pw.idrug.connections.domain.UpdateManager
import pw.idrug.connections.domain.UpdateState
import pw.idrug.connections.installer.ApkInstaller

class UpdateSettingsViewModel(
    private val updateManager: UpdateManager,
    private val installer: ApkInstaller
) : ViewModel() {

    private val _state = MutableStateFlow(UpdateSettingsUiState())
    val state: StateFlow<UpdateSettingsUiState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<UpdateSettingsEvent>()
    val events = _events.asSharedFlow()

    init {
        loadCachedState()
    }

    fun checkUpdates(force: Boolean = true) {
        viewModelScope.launch {
            _state.update { it.copy(buttonState = UpdateButtonState.CHECKING, showDialog = false) }
            when (val result = updateManager.check(force)) {
                is UpdateState.Available -> {
                    _state.update {
                        it.copy(
                            meta = result.meta,
                            isIgnored = false,
                            lastCheckedAt = updateManager.getLastCheckedAt().takeIf { ts -> ts > 0 },
                            buttonState = UpdateButtonState.AVAILABLE,
                            showDialog = true
                        )
                    }
                }
                UpdateState.NoUpdate -> {
                    val cachedMeta = updateManager.getPendingUpdate()
                    _state.update {
                        it.copy(
                            meta = cachedMeta,
                            isIgnored = cachedMeta?.let { meta -> updateManager.isIgnored(meta.versionCode) } ?: false,
                            lastCheckedAt = updateManager.getLastCheckedAt().takeIf { ts -> ts > 0 },
                            buttonState = UpdateButtonState.UP_TO_DATE,
                            showDialog = false
                        )
                    }
                    if (!force) {
                        _state.update { current -> current.copy(buttonState = UpdateButtonState.IDLE) }
                    } else {
                        _events.emit(UpdateSettingsEvent.UpToDate)
                    }
                }
                is UpdateState.Error -> {
                    _state.update {
                        it.copy(
                            buttonState = UpdateButtonState.ERROR,
                            lastCheckedAt = updateManager.getLastCheckedAt().takeIf { ts -> ts > 0 },
                            showDialog = false
                        )
                    }
                    _events.emit(UpdateSettingsEvent.Error(result.message))
                }
            }
        }
    }

    fun onDialogDismissed() {
        _state.update { it.copy(showDialog = false) }
    }

    fun onDialogIgnore() {
        val meta = _state.value.meta ?: return
        updateManager.ignore(meta.versionCode)
        _state.update {
            it.copy(
                isIgnored = true,
                showDialog = false,
                buttonState = UpdateButtonState.IDLE
            )
        }
        viewModelScope.launch { _events.emit(UpdateSettingsEvent.Ignored(meta.versionCode)) }
    }

    fun onDialogConfirmUpdate() {
        val meta = _state.value.meta ?: return
        val result = installer.download(meta)
        result.onSuccess {
            _state.update { it.copy(showDialog = false) }
            viewModelScope.launch { _events.emit(UpdateSettingsEvent.DownloadStarted) }
        }
        result.onFailure { error ->
            viewModelScope.launch {
                _events.emit(UpdateSettingsEvent.Error(error.localizedMessage ?: error.javaClass.simpleName))
            }
        }
    }

    fun onIgnoreToggle(checked: Boolean) {
        val meta = _state.value.meta ?: return
        if (checked) {
            updateManager.ignore(meta.versionCode)
        } else {
            updateManager.clearIgnore(meta.versionCode)
        }
        _state.update {
            it.copy(
                isIgnored = checked,
                buttonState = if (checked) UpdateButtonState.IDLE else determineButtonState(meta)
            )
        }
    }

    fun openDialogIfAvailable() {
        val meta = _state.value.meta ?: return
        if (determineButtonState(meta) == UpdateButtonState.AVAILABLE && !_state.value.isIgnored) {
            _state.update { it.copy(showDialog = true) }
        }
    }

    private fun loadCachedState() {
        val meta = updateManager.getPendingUpdate()
        val ignored = meta?.let { updateManager.isIgnored(it.versionCode) } ?: false
        val buttonState = if (meta != null && meta.versionCode > BuildConfig.VERSION_CODE && !ignored) {
            UpdateButtonState.AVAILABLE
        } else {
            UpdateButtonState.IDLE
        }
        _state.update {
            it.copy(
                meta = meta,
                isIgnored = ignored,
                lastCheckedAt = updateManager.getLastCheckedAt().takeIf { ts -> ts > 0 },
                buttonState = buttonState,
                showDialog = buttonState == UpdateButtonState.AVAILABLE
            )
        }
    }

    private fun determineButtonState(meta: UpdateMeta): UpdateButtonState {
        return if (meta.versionCode > BuildConfig.VERSION_CODE && !updateManager.isIgnored(meta.versionCode)) {
            UpdateButtonState.AVAILABLE
        } else {
            UpdateButtonState.IDLE
        }
    }

    companion object {
        fun factory(updateManager: UpdateManager, installer: ApkInstaller): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(UpdateSettingsViewModel::class.java)) {
                        return UpdateSettingsViewModel(updateManager, installer) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}

data class UpdateSettingsUiState(
    val buttonState: UpdateButtonState = UpdateButtonState.IDLE,
    val meta: UpdateMeta? = null,
    val isIgnored: Boolean = false,
    val lastCheckedAt: Long? = null,
    val showDialog: Boolean = false
)

enum class UpdateButtonState {
    IDLE,
    CHECKING,
    AVAILABLE,
    UP_TO_DATE,
    ERROR
}

sealed class UpdateSettingsEvent {
    data object DownloadStarted : UpdateSettingsEvent()
    data class Error(val message: String) : UpdateSettingsEvent()
    data class Ignored(val versionCode: Int) : UpdateSettingsEvent()
    data object UpToDate : UpdateSettingsEvent()
}
