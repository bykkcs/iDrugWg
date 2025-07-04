package pw.idrug.connections.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import pw.idrug.connections.util.DeviceUtils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (DeviceUtils.isTv(this)) {
            val tvIntent = Intent(this, TvEntryActivity::class.java).apply {
                action = intent?.action
                data = intent?.data
            }
            startActivity(tvIntent)
            finish()
            return
        }

        val targetIntent = when {
            isLoggedIn() || isOnboardingCompleted() -> Intent(this, MainActivity::class.java)
            else -> Intent(this, OnboardingActivity::class.java)
        }.apply {
            // propagate deep link data if any
            action = intent?.action
            data = intent?.data
        }
        startActivity(targetIntent)
        finish()
    }

    private fun isLoggedIn(): Boolean {
        return try {
            val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
            !prefs.getString("token", null).isNullOrEmpty()
        } catch (e: Exception) {
            Log.w(TAG, "Failed to read auth prefs", e)
            false
        }
    }

    private fun isOnboardingCompleted(): Boolean {
        return try {
            val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            prefs.getBoolean(KEY_ONBOARDING_SHOWN, false)
        } catch (e: Exception) {
            Log.w(TAG, "Failed to read onboarding flag", e)
            false
        }
    }

    companion object {
        private const val TAG = "SplashActivity"
        private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"
    }
}
