package ro.twodoors.chatbot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*
import ro.twodoors.chatbot.databinding.CardItemBinding

class ViewPagerAdapter(
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private val items = listOf(
            DataItem(
                    "Hello",
                    1,
                    ItemState.Start
            ),
            DataItem(
                    "Restaurant",
                    2,
                    ItemState.Unlock
            ),
            DataItem(
                    "Hotel",
                    3,
                    ItemState.Unlock
            ),
            DataItem(
                    "Tickets",
                    4,
                    ItemState.Unlock
            ),
            DataItem(
                    "Conversation",
                    5,
                    ItemState.Unlock
            ),
            DataItem(
                    "Shopping",
                    6,
                    ItemState.Unlock
            ),
            DataItem(
                    "Appointment",
                    7,
                    ItemState.Unlock
            ),
            DataItem(
                    "Taxi",
                    8,
                    ItemState.Unlock
            )
    )

    inner class ViewPagerViewHolder(binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(layoutInflater, parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentItem = items[position]
        holder.itemView.apply {
            tvTitle.text = currentItem.title
            tvPosition.text =  "${currentItem.position}/${items.size}"
            btnState.text = currentItem.state.name
            btnState.setOnClickListener {
                updateState(it, currentItem.state)
            }
        }
    }

    private fun updateState(view: View?, state: ItemState) {
        val btn = view as? AppCompatButton
        when(state){
            ItemState.Unlock -> btn?.text = ItemState.Start.name
            else -> return
        }
    }
}