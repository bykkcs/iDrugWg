package pw.idrug.connections.activity

import android.app.UiModeManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isLinked = prefs.getBoolean("is_linked", false)
        val isTv = isTvDevice()

        val target = if (isTv && !isLinked) {
            TVLinkActivity::class.java
        } else {
            MainActivity::class.java
        }

        startActivity(Intent(this, target))
        finish()
    }

    private fun isTvDevice(): Boolean {
        val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        if (uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION) {
            return true
        }
        return packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
    }
}
