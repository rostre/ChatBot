package ro.twodoors.chatbot

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.SeekBar
import androidx.core.content.ContextCompat


@SuppressLint("AppCompatCustomView")
class CustomSeekbar @JvmOverloads constructor(
    context: Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int = R.attr.seekBarStyle,
    defStyleRes : Int = 0
) : SeekBar(context, attrs, defStyleAttr, defStyleRes) {

    private val icons : ArrayList<Int> = arrayListOf(
        R.drawable.ic_hello,
        R.drawable.ic_restaurant,
        R.drawable.ic_hotel,
        R.drawable.ic_tickets,
        R.drawable.ic_conversation,
        R.drawable.ic_shopping,
        R.drawable.ic_calendar,
        R.drawable.ic_car
    )

    private val w = getPixelValueFromDP(32f)
    private val h = getPixelValueFromDP(32f)
    private val halfW =  (w / 2).toInt()
    private val halfH =  (h / 2).toInt()

    init {
        max = icons.size - 1
        progress = 1
        progressBackgroundTintList = ContextCompat.getColorStateList(context, android.R.color.transparent)
        progressTintList = ContextCompat.getColorStateList(context, android.R.color.transparent)
        thumbTintList = ContextCompat.getColorStateList(context, android.R.color.transparent)
        splitTrack = false
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawTickMarks(canvas)
    }

    private fun drawTickMarks(canvas: Canvas?) {
        canvas?.let {
            val count = icons.size
            val saveCount = canvas.save()
            canvas.translate(paddingLeft.toFloat(), (height / 2).toFloat())
            if (count > 1){
                val spacing = (width - paddingLeft - paddingRight) / (count - 1).toFloat()
                for (i in 0 until count){
                    val icon = icons[i]
                    val drawable = ContextCompat.getDrawable(context, icon) as LayerDrawable
                    drawable.setBounds(-halfW, -halfH, halfW, halfH)
                    drawable.setTintList(ContextCompat.getColorStateList(context, R.color.white))
                    drawable.draw(canvas)
                    canvas.translate(spacing, 0f)
                }
                canvas.restoreToCount(saveCount)
            }
        }
    }

    private fun getPixelValueFromDP(value : Float) : Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                value,
                context.resources.displayMetrics)
    }

}