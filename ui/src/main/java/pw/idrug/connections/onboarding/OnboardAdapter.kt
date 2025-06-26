package pw.idrug.connections.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pw.idrug.connections.R

data class OnboardPage(val emoji: String, val title: String, val text: String)

class OnboardAdapter(private val items: List<OnboardPage>) : RecyclerView.Adapter<OnboardAdapter.OnboardVH>() {
    class OnboardVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emoji: TextView = itemView.findViewById(R.id.img_onboard)
        val title: TextView = itemView.findViewById(R.id.title_onboard)
        val text: TextView = itemView.findViewById(R.id.text_onboard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_onboard_card, parent, false)
        return OnboardVH(v)
    }

    override fun onBindViewHolder(holder: OnboardVH, position: Int) {
        val item = items[position]
        holder.emoji.text = item.emoji
        holder.title.text = item.title
        holder.text.text = item.text
    }

    override fun getItemCount() = items.size
}
