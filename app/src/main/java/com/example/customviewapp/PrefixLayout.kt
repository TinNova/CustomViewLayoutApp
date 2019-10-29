package com.example.customviewapp

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.prefix_layout.view.*

class PrefixLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var title: String = ""
        set(value) {
            field = value
            textViewTitle.text = field
        }

    private var prefix: String = ""
        set(value) {
            field = value
            // We assign the prefix using the assignPrefix method in PrefixEditText.kt
            editTextCurrency.assignPrefix(field)
        }

    // We need this init block as we are taking the values straight from the XML
    init {

        inflate(context, R.layout.prefix_layout, this)

        //R.styleable has to be the same name as it is in attrs.xml
        context.obtainStyledAttributes(attrs, R.styleable.PrefixLayout).let {

            title = it.getString(R.styleable.PrefixLayout_title_prefix_layout).orEmpty()
            prefix = it.getString(R.styleable.PrefixLayout_prefix_prefix_layout).orEmpty()

            it.recycle()
        }
    }
}
