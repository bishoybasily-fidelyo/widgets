package com.fidelyo.ui.widget

import android.content.Context
import android.graphics.Color
import android.support.annotation.AttrRes
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.util.AttributeSet
import com.fidelyo.R

/**
 * Created by bishoy on 12/21/16.
 */
class HighlightTextView : AppCompatTextView {

    private val TAG = javaClass.simpleName

    private val HIGHLIGHT = R.color.colorAccent

    private var highlight = HIGHLIGHT

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initializeAttributes(attrs)
    }

    private fun initializeAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HighlightTextView)
        try {
            highlight = typedArray.getColor(R.styleable.HighlightTextView_highlightColor, Color.TRANSPARENT)
        } finally {
            typedArray.recycle()
        }
        initialize()
    }

    private fun initialize() {
        setTextNew(text)
    }

    fun setTextNew(charSequence: CharSequence?) {
        if (charSequence != null) {
            if (charSequence.isNotBlank()) {
                val spannableStringBuilder = SpannableStringBuilder(charSequence)
                spannableStringBuilder.setSpan(BackgroundColorSpan(highlight), 0, charSequence.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                text = spannableStringBuilder
                movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

}
