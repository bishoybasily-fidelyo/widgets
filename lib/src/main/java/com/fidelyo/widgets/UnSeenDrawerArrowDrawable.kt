package com.fidelyo.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.graphics.drawable.DrawerArrowDrawable

/**
 * Created by bishoy on 6/4/17.
 */

class UnSeenDrawerArrowDrawable(val context: Context) : DrawerArrowDrawable(context) {

    var showNotification = false
    var indicatorRadius = 15.0f

    override fun draw(canvas: Canvas) {

        paint.apply { color = Color.WHITE }

        super.draw(canvas)

        if (showNotification) {
            paint.apply { color = Color.RED }.apply { style = Paint.Style.FILL }
            canvas.drawCircle((intrinsicWidth / 4).toFloat(), (intrinsicHeight / 4).toFloat(), indicatorRadius, paint)
            paint.apply { style = Paint.Style.STROKE }
        }

    }

    fun showNotification(show: Boolean) {
        showNotification = show
        invalidateSelf()
    }

}
