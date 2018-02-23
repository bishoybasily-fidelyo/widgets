package com.fidelyo.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ViewFlipper

/**
 * Created by bishoy on 10/31/17.
 */

class ControllableViewFlipper : ViewFlipper {

    private val infinite = false

    var listener: OnStateChangedListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        updateStatus()
    }

    private fun updateStatus() {
        updateNextStatus()
        updatePreviousStatus()
    }

    override fun showNext() {
        if (canGoNext())
            return
        super.showNext()
        listener?.onPreviousChanged(true)
        updateNextStatus()
    }

    private fun updateNextStatus() {
        if (canGoNext())
            listener?.onNextChanged(false)
    }

    override fun showPrevious() {
        if (canGoPrevious())
            return
        super.showPrevious()
        listener?.onNextChanged(true)
        updatePreviousStatus()
    }

    private fun updatePreviousStatus() {
        if (canGoPrevious())
            listener?.onPreviousChanged(false)
    }

    private fun canGoNext() = displayedChild == childCount - 1 && !infinite

    private fun canGoPrevious() = displayedChild == 0 && !infinite

    fun hasNext(): Boolean {
        return displayedChild < childCount - 1
    }

    fun hasPrevious(): Boolean {
        return displayedChild > 0
    }

    interface OnStateChangedListener {

        fun onNextChanged(able: Boolean)
        fun onPreviousChanged(able: Boolean)

    }

}
