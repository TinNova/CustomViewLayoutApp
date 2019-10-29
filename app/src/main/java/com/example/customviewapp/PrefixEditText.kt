package com.example.customviewapp

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


class PrefixEditText(
    context: Context,
    attributes: AttributeSet
) : AppCompatEditText(context, attributes) {

    companion object {
        private const val DEFAULT_PADDING = -1f
    }
    private var defaultLeftPadding = DEFAULT_PADDING

    private var prefix: String = ""
        set(value) {
            field = value
            calculatePrefix(field)
            requestLayout()
            invalidate()
        }

    /** init block only needed to assign prefix via XML*/
//    init {
//        context.obtainStyledAttributes(attributes, R.styleable.PrefixEditText).let {
//
//            prefix = it.getString(R.styleable.PrefixEditText_prefix).orEmpty()
//            it.recycle()
//        }
//    }

    fun assignPrefix(prefix: String){
        this.prefix = prefix
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        calculatePrefix(prefix)
    }

    private fun calculatePrefix(prefix: String) {
        // if leftPadding is -1f, then it is a new EditText that hasn't had it's prefix set yet
        if (defaultLeftPadding == DEFAULT_PADDING) {
            val widths = FloatArray(prefix.length)
            paint.getTextWidths(prefix, widths)
            var textWidth = 0f
            for (w in widths) {
                textWidth += w
            }
            defaultLeftPadding = compoundPaddingLeft.toFloat()
            setPadding(
                (textWidth + defaultLeftPadding).toInt(),
                paddingRight, paddingTop, paddingBottom
            ) //paddingBottom creates a padding between the text and the underline in an EditText
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(
            prefix, defaultLeftPadding,
            getLineBounds(0, null).toFloat(), paint
        )
    }
}
