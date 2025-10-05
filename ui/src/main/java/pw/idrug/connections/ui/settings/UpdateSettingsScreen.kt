package pw.idrug.connections.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.R
import pw.idrug.connections.ui.dialogs.UpdateDialog
import java.text.DateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateSettingsScreen(
    viewModel: UpdateSettingsViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                UpdateSettingsEvent.DownloadStarted -> snackbarHostState.showSnackbar(context.getString(R.string.update_download_started))
                is UpdateSettingsEvent.Error -> snackbarHostState.showSnackbar(event.message)
                is UpdateSettingsEvent.Ignored -> snackbarHostState.showSnackbar(
                    context.getString(R.string.update_version_hidden, event.versionCode)
                )
                UpdateSettingsEvent.UpToDate -> snackbarHostState.showSnackbar(
                    context.getString(R.string.update_snackbar_latest)
                )
            }
        }
    }

    if (state.showDialog && state.meta != null && state.meta.versionCode > BuildConfig.VERSION_CODE) {
        UpdateDialog(
            meta = state.meta,
            onUpdate = { viewModel.onDialogConfirmUpdate() },
            onLater = { viewModel.onDialogDismissed() },
            onIgnore = { viewModel.onDialogIgnore() }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.update_settings_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.back))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        UpdateSettingsContent(
            padding = padding,
            state = state,
            onButtonClick = {
                if (state.buttonState == UpdateButtonState.AVAILABLE) {
                    viewModel.openDialogIfAvailable()
                } else {
                    viewModel.checkUpdates(force = true)
                }
            },
            onToggleIgnore = viewModel::onIgnoreToggle
        )
    }
}

@Composable
private fun UpdateSettingsContent(
    padding: PaddingValues,
    state: UpdateSettingsUiState,
    onButtonClick: () -> Unit,
    onToggleIgnore: (Boolean) -> Unit
) {
    val buttonText = when (state.buttonState) {
        UpdateButtonState.CHECKING -> stringResource(id = R.string.update_button_checking)
        UpdateButtonState.AVAILABLE -> stringResource(id = R.string.update_button_available)
        UpdateButtonState.UP_TO_DATE -> stringResource(id = R.string.update_button_uptodate)
        UpdateButtonState.ERROR -> stringResource(id = R.string.update_button_error)
        UpdateButtonState.IDLE -> stringResource(id = R.string.update_button_default)
    }
    val showLoading = state.buttonState == UpdateButtonState.CHECKING
    val meta = state.meta
    val shouldShowToggle = meta != null && meta.versionCode > BuildConfig.VERSION_CODE
    val lastCheckedText = state.lastCheckedAt?.let {
        val formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
        formatter.format(Date(it))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.update_settings_description),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onButtonClick,
            enabled = state.buttonState != UpdateButtonState.CHECKING
        ) {
            if (showLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .align(Alignment.CenterVertically),
                    strokeWidth = 2.dp
                )
            }
            Text(text = buttonText)
        }
        if (lastCheckedText != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.update_last_checked, lastCheckedText),
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (meta != null) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.update_current_available_version, meta.versionCode),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if (shouldShowToggle) {
            Spacer(modifier = Modifier.height(12.dp))
            RowWithSwitch(
                checked = state.isIgnored,
                onCheckedChange = onToggleIgnore
            )
        }
    }
}

@Composable
private fun RowWithSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id = R.string.update_hide_version))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
