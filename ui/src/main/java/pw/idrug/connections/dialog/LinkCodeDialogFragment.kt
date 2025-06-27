package pw.idrug.connections.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.fragment.app.DialogFragment
import okhttp3.*
import org.json.JSONObject
import pw.idrug.connections.R
import java.io.IOException

class LinkCodeDialogFragment : DialogFragment() {
    private var timer: CountDownTimer? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_link_code, null)
        val codeText: TextView = view.findViewById(R.id.code_text)
        val timerText: TextView = view.findViewById(R.id.timer_text)
        fetchCode(codeText, timerText)
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(view)
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
    }

    private fun fetchCode(codeText: TextView, timerText: TextView) {
        val prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val token = prefs.getString("token", null)
        if (token == null) {
            codeText.text = getString(R.string.generic_error)
            return
        }
        val client = OkHttpClient()
        val req = Request.Builder()
            .url("https://idrug.pw/api/linking/generate")
            .addHeader("Authorization", "Bearer $token")
            .post(FormBody.Builder().build())
            .build()
        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread { codeText.text = getString(R.string.generic_error) }
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val obj = JSONObject(response.body?.string() ?: "{}")
                    val code = obj.optString("link_code")
                    val ttl = obj.optInt("ttl", 180)
                    runOnUiThread {
                        codeText.text = code
                        timer = object : CountDownTimer(ttl * 1000L, 1000L) {
                            override fun onTick(ms: Long) {
                                timerText.text = getString(R.string.code_expires_in, ms / 1000)
                            }
                            override fun onFinish() {
                                timerText.text = getString(R.string.code_expired)
                            }
                        }.start()
                    }
                } else {
                    runOnUiThread { codeText.text = getString(R.string.generic_error) }
                }
            }
        })
    }

    private fun runOnUiThread(block: () -> Unit) {
        activity?.runOnUiThread(block)
    }
}
