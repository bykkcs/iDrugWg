package pw.idrug.connections.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pw.idrug.connections.R

class OnboardingAdapter(private val items: List<Item>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    data class Item(val emoji: String, val title: String, val desc: String)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emoji: TextView = view.findViewById(R.id.text_emoji)
        val title: TextView = view.findViewById(R.id.text_title)
        val desc: TextView = view.findViewById(R.id.text_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_onboard_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.emoji.text = item.emoji
        holder.title.text = item.title
        holder.desc.text = item.desc
    }
}
