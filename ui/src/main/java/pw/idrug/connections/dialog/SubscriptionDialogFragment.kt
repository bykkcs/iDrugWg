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
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import org.json.JSONObject
import pw.idrug.connections.R

class SubscriptionDialogFragment : DialogFragment() {

    private lateinit var locationSpinner: Spinner
    private lateinit var durationSpinner: Spinner
    private lateinit var priceText: TextView
    private lateinit var payButton: View

    private val locations = listOf(
        "germany" to R.string.server_germany,
        "bulgaria" to R.string.server_bulgaria,
        "madrid" to R.string.server_madrid,
        "multihop" to R.string.server_multihop_germany
    )
    private val durations = listOf(
        30 to R.string.duration_1_month,
        90 to R.string.duration_3_months,
        365 to R.string.duration_1_year
    )

    private val prices: Map<String, Map<Int, Int>> = mapOf(
        "germany" to mapOf(30 to 199, 90 to 549, 365 to 1799),
        "bulgaria" to mapOf(30 to 199, 90 to 549, 365 to 1799),
        "madrid" to mapOf(30 to 199, 90 to 549, 365 to 1799),
        "multihop" to mapOf(30 to 299, 90 to 799, 365 to 2599)
    )

    private val client = OkHttpClient()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ctx = requireContext()
        val view = LayoutInflater.from(ctx).inflate(R.layout.dialog_subscription, null)
        locationSpinner = view.findViewById(R.id.spinner_location)
        durationSpinner = view.findViewById(R.id.spinner_duration)
        priceText = view.findViewById(R.id.text_price)
        payButton = view.findViewById(R.id.pay_button)

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

        payButton.setOnClickListener {
            sendPaymentRequest()
        }

        return MaterialAlertDialogBuilder(ctx, R.style.MonetAlertDialog)
            .setView(view)
            .create()
    }

    private fun updatePrice() {
        val locKey = locations[locationSpinner.selectedItemPosition].first
        val duration = durations[durationSpinner.selectedItemPosition].first
        val price = prices[locKey]?.get(duration) ?: 0
        priceText.text = getString(R.string.price_template, price)
    }

    private fun sendPaymentRequest() {
        val prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val token = prefs.getString("telegram_id", null) ?: return
        val location = locations[locationSpinner.selectedItemPosition].first
        val duration = durations[durationSpinner.selectedItemPosition].first

        val formBody = FormBody.Builder()
            .add("location", location)
            .add("duration", duration.toString())
            .add("user_id", token)
            .build()

        val req = Request.Builder()
            .url("https://idrug.pw/api/pay")
            .post(formBody)
            .build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showToast(getString(R.string.connection_error, e.message ?: ""))
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "{}")
                    val url = json.optString("payment_url")
                    if (url.isNotEmpty()) {
                        openPaymentPage(url)
                    } else {
                        showToast(getString(R.string.generic_error))
                    }
                } else {
                    showToast(getString(R.string.generic_error))
                }
            }
        })
    }

    private fun openPaymentPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun showToast(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}
