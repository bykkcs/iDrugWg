package pw.idrug.connections.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import pw.idrug.connections.R
import pw.idrug.connections.activity.MainActivity

class OnboardingActivity : AppCompatActivity() {

    private var viewPager: ViewPager2? = null
    private var errorText: TextView? = null
    private var nextButton: Button? = null
    private lateinit var adapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isLoggedIn()) {
            startMain()
            return
        }
        setContentView(R.layout.activity_onboarding)
        try {
            viewPager = findViewById(R.id.view_pager)
            errorText = findViewById(R.id.error_text)
            nextButton = findViewById(R.id.btn_next)
        } catch (e: Exception) {
            Log.e(TAG, "Unable to find onboarding views", e)
        }

        val items = loadItems()
        if (items.isEmpty()) {
            viewPager?.visibility = View.GONE
            errorText?.visibility = View.VISIBLE
            nextButton?.text = getString(android.R.string.ok)
            nextButton?.setOnClickListener { finish() }
            return
        }
        adapter = OnboardingAdapter(items)
        viewPager?.adapter = adapter

        nextButton?.setOnClickListener {
            val next = (viewPager?.currentItem ?: 0) + 1
            if (next < adapter.itemCount) {
                viewPager?.currentItem = next
            } else {
                finishOnboarding()
            }
        }
    }

    private fun isLoggedIn(): Boolean {
        return try {
            val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
            !prefs.getString("token", null).isNullOrEmpty()
        } catch (e: Exception) {
            Log.w(TAG, "Unable to access preferences", e)
            false
        }
    }

    private fun loadItems(): List<OnboardingItem> {
        val emojis = safeArray(R.array.onboarding_emojis)
        val titles = safeArray(R.array.onboarding_titles)
        val descs = safeArray(R.array.onboarding_descriptions)
        val count = listOf(emojis.size, titles.size, descs.size).minOrNull() ?: 0
        if (count == 0) return emptyList()
        val list = mutableListOf<OnboardingItem>()
        for (i in 0 until count) {
            list.add(OnboardingItem(emojis[i], titles[i], descs[i]))
        }
        return list
    }

    private fun safeArray(resId: Int): Array<String> {
        return try {
            resources.getStringArray(resId)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading array $resId", e)
            emptyArray()
        }
    }

    private fun finishOnboarding() {
        try {
            val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("onboarding_done", true).apply()
        } catch (e: Exception) {
            Log.w(TAG, "Unable to save onboarding flag", e)
        }
        startMain(openAccount = true)
    }

    private fun startMain(openAccount: Boolean = false) {
        val intent = Intent(this, MainActivity::class.java)
        if (openAccount) {
            intent.putExtra(MainActivity.EXTRA_OPEN_ACCOUNT, true)
        }
        startActivity(intent)
        finish()
    }

    data class OnboardingItem(val emoji: String, val title: String, val desc: String)

    private class OnboardingAdapter(private val items: List<OnboardingItem>) :
        RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_onboarding_card, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.emoji.text = item.emoji
            holder.title.text = item.title
            holder.desc.text = item.desc
        }

        override fun getItemCount(): Int = items.size

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val emoji: TextView = view.findViewById(R.id.text_emoji)
            val title: TextView = view.findViewById(R.id.text_title)
            val desc: TextView = view.findViewById(R.id.text_desc)
        }
    }

    companion object {
        private const val TAG = "OnboardingActivity"
    }
}
