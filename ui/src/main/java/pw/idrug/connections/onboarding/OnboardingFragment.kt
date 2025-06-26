package pw.idrug.connections.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import pw.idrug.connections.R

class OnboardingFragment : Fragment() {

    private val onboardPages = listOf(
        OnboardPage("🔒", "Anonymous & Secure", "iDrug VPN hides your real IP and encrypts your data."),
        OnboardPage("⚡", "Fast Servers", "Connect instantly, no speed limits. Choose server location."),
        OnboardPage("🎮", "Game/Streaming Mode", "No pings, no drops, for all your devices."),
        OnboardPage("💸", "Pay Any Way", "Crypto, card, whatever. Instant setup, 24/7 support."),
        OnboardPage("🌙", "No Logs. Ever.", "Zero bullshit. No log storage, no tracking, period.")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pager = view.findViewById<ViewPager2>(R.id.onboard_pager)
        pager.adapter = OnboardAdapter(onboardPages)

        val btnNext = view.findViewById<Button>(R.id.btn_onboard_next)
        btnNext.setOnClickListener {
            if (pager.currentItem < onboardPages.size - 1) {
                pager.currentItem += 1
            } else {
                // TODO: переход к логину/главному экрану
                requireActivity().supportFragmentManager.popBackStack()
            }
        }

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                btnNext.text = if (position == onboardPages.lastIndex) "Начать" else "Далее"
            }
        })
    }
}
