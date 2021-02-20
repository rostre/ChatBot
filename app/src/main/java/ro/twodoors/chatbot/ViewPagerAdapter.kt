package ro.twodoors.chatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.card_item.view.*

class ViewPagerAdapter(val items : List<DataItem>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    val images = listOf(
        R.drawable.photo_1,
        R.drawable.photo_2,
        R.drawable.photo_3,
        R.drawable.photo_4,
        R.drawable.photo_5,
        R.drawable.photo_6,
        R.drawable.photo_7,
        R.drawable.photo_8
    )

    inner class ViewPagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentItem = items[position]
        holder.itemView.tvTitle.text = currentItem.title
        holder.itemView.tvPosition.text =  "${currentItem.position}/${items.size}"
        holder.itemView.btnState.text = currentItem.state
        //holder.itemView.card_content.setBackgroundResource(images[position])

//        Glide.with(holder.itemView.context)
//            .load(images[position])
//            .centerCrop()
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .into(holder.itemView.card_content)

    }
}