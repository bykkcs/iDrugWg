package pw.idrug.connections.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pw.idrug.connections.R

class SubscriptionDialogFragment : DialogFragment() {

    private lateinit var locationSpinner: Spinner
    private lateinit var durationSpinner: Spinner
    private lateinit var priceText: TextView

    private val locations = listOf(
        "germany" to R.string.server_germany,
        "bulgaria" to R.string.server_bulgaria,
        "madrid" to R.string.server_madrid,
        "multihop" to R.string.server_multihop_germany
    )
    private val durations = listOf(
        1 to R.string.duration_1_month,
        3 to R.string.duration_3_months,
        12 to R.string.duration_1_year
    )

    private val prices: Map<String, Map<Int, Int>> = mapOf(
        "germany" to mapOf(1 to 250, 3 to 600, 12 to 2000),
        "bulgaria" to mapOf(1 to 250, 3 to 600, 12 to 2000),
        "madrid" to mapOf(1 to 250, 3 to 600, 12 to 2000),
        "multihop" to mapOf(1 to 300, 3 to 700, 12 to 2200)
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ctx = requireContext()
        val view = LayoutInflater.from(ctx).inflate(R.layout.dialog_subscription, null)
        locationSpinner = view.findViewById(R.id.spinner_location)
        durationSpinner = view.findViewById(R.id.spinner_duration)
        priceText = view.findViewById(R.id.text_price)

        val locAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, locations.map { getString(it.second) })
        locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner.adapter = locAdapter

        val durAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, durations.map { getString(it.second) })
        durAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        durationSpinner.adapter = durAdapter

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                updatePrice()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                updatePrice()
            }
        }
        locationSpinner.onItemSelectedListener = listener
        durationSpinner.onItemSelectedListener = listener
        updatePrice()

        return MaterialAlertDialogBuilder(ctx, R.style.MonetAlertDialog)
            .setView(view)
            .setPositiveButton(R.string.pay) { _, _ ->
                startPayment()
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun updatePrice() {
        val locKey = locations[locationSpinner.selectedItemPosition].first
        val duration = durations[durationSpinner.selectedItemPosition].first
        val price = prices[locKey]?.get(duration) ?: 0
        priceText.text = getString(R.string.price_template, price)
    }

    private fun startPayment() {
        val prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val token = prefs.getString("token", null) ?: return
        val location = locations[locationSpinner.selectedItemPosition].first
        val duration = durations[durationSpinner.selectedItemPosition].first
        val url = generatePaymentLink(location, duration, token)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun generatePaymentLink(location: String, duration: Int, token: String): String {
        val baseUrl = "https://idrug.pw/api/pay"
        return "$baseUrl?location=$location&duration=$duration&token=$token"
    }
}
