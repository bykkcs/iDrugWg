package pw.idrug.connections.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import android.widget.ProgressBar
import kotlinx.coroutines.launch
import pw.idrug.connections.R
import pw.idrug.connections.speedtest.SpeedTestHttpClient
import pw.idrug.connections.speedtest.SpeedTestStatus
import pw.idrug.connections.speedtest.SpeedTestUiState
import pw.idrug.connections.speedtest.SpeedTestViewModel

class SpeedTestFragment : Fragment() {

    private val viewModel: SpeedTestViewModel by viewModels()

    private lateinit var statusText: TextView
    private lateinit var progressIndicator: ProgressBar
    private lateinit var downloadChips: ChipGroup
    private lateinit var uploadChips: ChipGroup
    private lateinit var buttonStart: MaterialButton
    private lateinit var buttonCancel: MaterialButton
    private lateinit var textPingValue: TextView
    private lateinit var textDownloadValue: TextView
    private lateinit var textUploadValue: TextView
    private lateinit var textError: TextView
    private lateinit var textLastUpdated: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val apiKey = prefs.getString("speed_api_key", null)
        SpeedTestHttpClient.configure(
            baseUrl = "https://idrug.pw",
            apiKeyProvider = { apiKey },
            enableLogging = false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_speed_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupChips()
        setupButtons()
        observeState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.cancelTests()
    }

    private fun bindViews(view: View) {
        statusText = view.findViewById(R.id.text_status)
        progressIndicator = view.findViewById(R.id.progress_indicator)
        downloadChips = view.findViewById(R.id.chip_group_download)
        uploadChips = view.findViewById(R.id.chip_group_upload)
        buttonStart = view.findViewById(R.id.button_start)
        buttonCancel = view.findViewById(R.id.button_cancel)
        textPingValue = view.findViewById(R.id.text_ping_value)
        textDownloadValue = view.findViewById(R.id.text_download_value)
        textUploadValue = view.findViewById(R.id.text_upload_value)
        textError = view.findViewById(R.id.text_error)
        textLastUpdated = view.findViewById(R.id.text_last_updated)
    }

    private fun setupChips() {
        populateChipGroup(
            downloadChips,
            SpeedTestViewModel.DOWNLOAD_OPTIONS_BYTES,
            DOWNLOAD_PRESET_LABELS,
            viewModel.state.value.downloadBytes
        ) { value ->
            viewModel.setDownloadSize(value)
        }
        populateChipGroup(
            uploadChips,
            SpeedTestViewModel.UPLOAD_OPTIONS_BYTES,
            UPLOAD_PRESET_LABELS,
            viewModel.state.value.uploadBytes
        ) { value ->
            viewModel.setUploadSize(value)
        }
    }

    private fun setupButtons() {
        buttonStart.setOnClickListener { viewModel.startTests() }
        buttonCancel.setOnClickListener { viewModel.cancelTests() }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: SpeedTestUiState) {
        val ctx = requireContext()

        ensureChipSelection(downloadChips, state.downloadBytes)
        ensureChipSelection(uploadChips, state.uploadBytes)

        statusText.text = ctx.getString(statusMessageRes(state.status))

        progressIndicator.visibility = if (state.isRunning) View.VISIBLE else View.GONE

        buttonStart.isEnabled = !state.isRunning
        buttonCancel.isEnabled = state.isRunning
        setChipGroupEnabled(downloadChips, !state.isRunning)
        setChipGroupEnabled(uploadChips, !state.isRunning)

        textPingValue.text = state.pingRttLabel
        textDownloadValue.text = state.downloadThroughputLabel
        textUploadValue.text = state.uploadThroughputLabel

        val error = state.errorMessage
        if (error.isNullOrBlank()) {
            textError.visibility = View.GONE
        } else {
            textError.visibility = View.VISIBLE
            textError.text = ctx.getString(R.string.speed_test_error_format, error)
        }

        val lastUpdatedSeconds = state.lastUpdatedSeconds
        if (lastUpdatedSeconds != null && state.status == SpeedTestStatus.FINISHED) {
            textLastUpdated.visibility = View.VISIBLE
            textLastUpdated.text = ctx.getString(
                R.string.speed_test_last_updated_format,
                lastUpdatedSeconds
            )
        } else {
            textLastUpdated.visibility = View.GONE
        }
    }

    private fun statusMessageRes(status: SpeedTestStatus): Int = when (status) {
        SpeedTestStatus.IDLE -> R.string.speed_test_status_idle
        SpeedTestStatus.PINGING -> R.string.speed_test_status_pinging
        SpeedTestStatus.PING_DONE -> R.string.speed_test_status_ping_done
        SpeedTestStatus.DOWNLOAD_RUNNING -> R.string.speed_test_status_download_running
        SpeedTestStatus.DOWNLOAD_DONE -> R.string.speed_test_status_download_done
        SpeedTestStatus.UPLOAD_RUNNING -> R.string.speed_test_status_upload_running
        SpeedTestStatus.FINISHED -> R.string.speed_test_status_finished
        SpeedTestStatus.CANCELLED -> R.string.speed_test_status_cancelled
        SpeedTestStatus.ERROR -> R.string.speed_test_status_error
    }

    private fun populateChipGroup(
        group: ChipGroup,
        values: List<Long>,
        labels: IntArray,
        initialValue: Long,
        onSelected: (Long) -> Unit
    ) {
        group.setOnCheckedStateChangeListener(null)
        group.removeAllViews()
        var hasSelection = false
        values.forEachIndexed { index, value ->
            val chip = Chip(requireContext()).apply {
                id = View.generateViewId()
                isCheckable = true
                text = getString(labels.getOrElse(index) { labels.last() })
                tag = value
            }
            group.addView(chip)
            if (value == initialValue && !hasSelection) {
                chip.isChecked = true
                hasSelection = true
            }
        }
        if (!hasSelection && group.childCount > 0) {
            val firstChip = group.getChildAt(0) as? Chip
            firstChip?.isChecked = true
            firstChip?.tag?.let { (it as? Long)?.let(onSelected) }
        }
        group.setOnCheckedStateChangeListener { chipGroup, checkedIds ->
            val chipId = checkedIds.firstOrNull() ?: return@setOnCheckedStateChangeListener
            val chip = chipGroup.findViewById<Chip>(chipId) ?: return@setOnCheckedStateChangeListener
            val value = chip.tag as? Long ?: return@setOnCheckedStateChangeListener
            onSelected(value)
        }
    }

    private fun ensureChipSelection(group: ChipGroup, value: Long) {
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i) as? Chip ?: continue
            val chipValue = chip.tag as? Long ?: continue
            if (chipValue == value) {
                if (!chip.isChecked) {
                    group.check(chip.id)
                }
                return
            }
        }
    }

    private fun setChipGroupEnabled(group: ChipGroup, enabled: Boolean) {
        for (i in 0 until group.childCount) {
            group.getChildAt(i)?.isEnabled = enabled
        }
    }

    companion object {
        private val DOWNLOAD_PRESET_LABELS = intArrayOf(
            R.string.speed_test_preset_quick,
            R.string.speed_test_preset_standard,
            R.string.speed_test_preset_extended,
            R.string.speed_test_preset_full
        )
        private val UPLOAD_PRESET_LABELS = intArrayOf(
            R.string.speed_test_preset_quick,
            R.string.speed_test_preset_standard,
            R.string.speed_test_preset_extended,
            R.string.speed_test_preset_full
        )
    }
}
