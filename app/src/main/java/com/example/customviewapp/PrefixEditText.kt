package com.example.customviewapp

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


class PrefixEditText(
    context: Context,
    attributes: AttributeSet
) : AppCompatEditText(context, attributes) {

    // this is the original padding of the EditText, we will use i
    companion object {
        private const val DEFAULT_PADDING = -1f
    }
    private var defaultLeftPadding = DEFAULT_PADDING
    private var prefix = "€"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        calculatePrefix()
    }

    private fun calculatePrefix() {
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
