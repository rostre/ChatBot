package ro.twodoors.chatbot

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlin.math.abs

class ViewPagerPageTransformer : ViewPager2.PageTransformer{

    override fun transformPage(view: View, position: Float) {
        view.apply {

            val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
            val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
            val offset = position * -(2 * offsetPx + pageMarginPx)
            view.translationX = offset

            view.scaleY = 1 - (0.25f * abs(position))
            //view.alpha = 0.25f + (1 - abs(position))

//            val pageWidth = width
//            val pageHeight = height
//            view.translationX = -(offsetPx + pageMarginPx) * position
//
//            val backGr = view.findViewById<ImageView>(R.id.card_content)
//            val card = view.findViewById<CardView>(R.id.cardView)
//
//            when{
//                    position < -1 -> {
//
//                    }
//
//                    position <= 0 -> { // [-1,0]
//
//                        //view.translationX = pageWidth * (-position)
//                        if (card != null){
//                            card.translationX = pageWidth * position
//                        }
//
//                        if (backGr != null){
//                            backGr.alpha = 1 + position
//                        }
//
//                    }
//                    position <= 1 -> { // (0,1]
//
//                        //view.translationX = pageWidth * (-position)
//                        if (card != null){
//                            card.translationX = pageWidth * position
//                        }
//
//                        if (backGr != null){
//                            backGr.alpha = 1 - position
//                        }
//
//                    }
//                    else -> {
//                    }
//                }
        }
    }
}