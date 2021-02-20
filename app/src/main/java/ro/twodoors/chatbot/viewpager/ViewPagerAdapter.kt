package ro.twodoors.chatbot.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*
import ro.twodoors.chatbot.data.DataItem
import ro.twodoors.chatbot.data.ItemState
import ro.twodoors.chatbot.databinding.CardItemBinding

class ViewPagerAdapter(private val items : List<DataItem>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

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
                updateState(it)
            }
        }
    }

    private fun updateState(view: View?) {
        val btn = view as? AppCompatButton
        when(btn?.text?.toString()){
            ItemState.Unlock.name -> btn.text = ItemState.Start.name
            else -> return
        }
    }
}