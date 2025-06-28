package pw.idrug.connections.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import pw.idrug.connections.fragment.OnboardingFragment

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        if (!prefs.getString("token", null).isNullOrEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(android.R.id.content, OnboardingFragment())
            }
        }
    }
}
