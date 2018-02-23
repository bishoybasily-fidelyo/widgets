package com.fidelyo.ui.widget

import android.content.Context
import android.graphics.Color
import android.support.annotation.AttrRes
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import com.fidelyo.R


/**
 * Created by bishoy on 12/21/16.
 */
class ReadMoreTextView : AppCompatTextView {

    private var expanded = false

    private val MORE = " Read more..."
    private val LESS = " Less"
    private val MAX_CHARACTERS_LENGTH = 50
    private val MAX_LINES_LENGTH = 2

    private var expandText: String = MORE
    private var collapseText: String = LESS
    private var maxCharactersLength: Int = MAX_CHARACTERS_LENGTH
    private var maxLinesLength: Int = MAX_LINES_LENGTH

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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReadMoreTextView)
        try {

            val et = typedArray.getString(R.styleable.ReadMoreTextView_expandText)
            val ct = typedArray.getString(R.styleable.ReadMoreTextView_collapseText)
            if (!TextUtils.isEmpty(et)) expandText = et
            if (!TextUtils.isEmpty(ct)) collapseText = ct

            maxCharactersLength = typedArray.getInteger(R.styleable.ReadMoreTextView_maxCharacters, MAX_CHARACTERS_LENGTH)
            maxLinesLength = typedArray.getInteger(R.styleable.ReadMoreTextView_maxLines, MAX_LINES_LENGTH)

        } finally {
            typedArray.recycle()
        }
        initialize()
    }

    private fun initialize() {
        setTextNew(text.toString())
    }

    private fun setSpannable(charSequence: CharSequence) {
        val textWithSpan = charSequence.toString() + clickableText()
        val spannableStringBuilder = SpannableStringBuilder(textWithSpan)
        spannableStringBuilder.setSpan(object : ClickableSpan() {

            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = Color.BLUE
            }

            override fun onClick(view: View) {
                if (expanded) {
                    expanded = false
                    if (willCollapse(charSequence)) {
                        setTrimmedText(charSequence)
                    } else {
                        setSpannable(text)
                    }
                } else {
                    expanded = true
                    setSpannable(text)
                }
            }

        }, textWithSpan.length - clickableText().length, textWithSpan.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = spannableStringBuilder
        movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setTrimmedText(text: CharSequence) {
        if (text.split("\r\n|\r|\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size > maxLinesLength) {
            val firstLineIndex = text.indexOf("\n")
            setSpannable(text.substring(0, text.indexOf("\n", firstLineIndex + 1)))
        } else if (text.length > maxCharactersLength) {
            setSpannable(text.substring(0, maxCharactersLength))
        }
    }

    fun setTextNew(charSequence: String) {
        if (charSequence.isNotBlank()) {
            expanded = false
            if (willCollapse(charSequence)) {
                text = charSequence
                if (willCollapse(charSequence)) {
                    setTrimmedText(charSequence)
                } else {
                    setSpannable(charSequence)
                }
            } else {
                text = charSequence
            }
        } else {
            text = null
        }
    }

    fun clickableText(): String {
        return if (expanded) collapseText else expandText
    }

    fun willCollapse(charSequence: CharSequence): Boolean {
        return charSequence.length > maxCharactersLength || charSequence.split("\r\n|\r|\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size > maxLinesLength
    }


}
