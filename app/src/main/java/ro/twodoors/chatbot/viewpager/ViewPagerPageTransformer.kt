package ro.twodoors.chatbot.viewpager

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import ro.twodoors.chatbot.R
import kotlin.math.abs

class ViewPagerPageTransformer : ViewPager2.PageTransformer{

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
            val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
            val offset = position * -(2 * offsetPx + pageMarginPx)

            view.translationX = offset
            view.scaleY = 1 - (0.25f * abs(position))
            //view.alpha = 1-Math.abs(position)
        }
    }
}