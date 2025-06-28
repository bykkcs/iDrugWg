package pw.idrug.connections.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import pw.idrug.connections.R
import pw.idrug.connections.activity.MainActivity

class OnboardingFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var nextButton: Button
    private lateinit var adapter: OnboardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val emojis = resources.getStringArray(R.array.onboard_emojis)
        val titles = resources.getStringArray(R.array.onboard_titles)
        val descs = resources.getStringArray(R.array.onboard_descriptions)
        val size = listOf(emojis.size, titles.size, descs.size).minOrNull() ?: 0
        val items = List(size) { i ->
            OnboardingAdapter.Item(emojis[i], titles[i], descs[i])
        }
        viewPager = view.findViewById(R.id.viewPager)
        nextButton = view.findViewById(R.id.button_next)
        adapter = OnboardingAdapter(items)
        viewPager.adapter = adapter

        if (adapter.itemCount == 0) {
            // Nothing to show, immediately continue
            startActivity(Intent(requireContext(), MainActivity::class.java).apply {
                putExtra(MainActivity.EXTRA_OPEN_ACCOUNT, true)
            })
            requireActivity().finish()
            return
        }

        updateButtonText()

        nextButton.setOnClickListener {
            if (viewPager.currentItem < adapter.itemCount - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                startActivity(Intent(requireContext(), MainActivity::class.java).apply {
                    putExtra(MainActivity.EXTRA_OPEN_ACCOUNT, true)
                })
                requireActivity().finish()
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButtonText()
            }
        })
    }

    private fun updateButtonText() {
        nextButton.text = if (::adapter.isInitialized && viewPager.currentItem >= adapter.itemCount - 1) {
            getString(R.string.onboarding_start)
        } else {
            getString(R.string.onboarding_next)
        }
    }
}
