package ro.twodoors.chatbot.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat

fun View.startCircularReveal(posX : Int, posY : Int) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int,
                                    oldRight: Int, oldBottom: Int) {
            v.removeOnLayoutChangeListener(this)

            val radius = Math.hypot( right.toDouble(), bottom.toDouble()).toInt()
            ViewAnimationUtils.createCircularReveal(v, posX, posY, 0f, radius.toFloat()).apply {
                duration = 1000
                start()
            }
        }
    })
}

fun View.exitCircularReveal(exitX: Int, exitY: Int, block: () -> Unit) {
    val startRadius = Math.hypot(this.width.toDouble(), this.height.toDouble())
    ViewAnimationUtils.createCircularReveal(this, exitX, exitY, startRadius.toFloat(), 0f).apply {
        duration = 1000
        interpolator = DecelerateInterpolator(2f)
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                block()
                super.onAnimationEnd(animation)
            }
        })
        start()
    }
}

fun View.getViewCenter() = Point(
        (x + width / 2).toInt(),
        (y + height / 2).toInt())


fun Drawable.setIconColor(context: Context, color : Int){
    this.setTintList(ContextCompat.getColorStateList(context, color))
}
