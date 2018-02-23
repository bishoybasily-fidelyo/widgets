package com.fidelyo.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import com.fidelyo.R
import com.fidelyo.helper.ResourcesHelper

/**
 * Created by bishoy on 6/4/17.
 */

class UnSeenDrawerArrowDrawable(val context: Context,
                                val resourcesHelper: ResourcesHelper) : DrawerArrowDrawable(context) {

    var showNotification = false
    var indicatorRadius = 15.0f

    override fun draw(canvas: Canvas) {

        paint.apply { color = resourcesHelper.color(R.color.white) }

        super.draw(canvas)

        if (showNotification) {
            paint.apply { color = resourcesHelper.color(R.color.red) }.apply { style = Paint.Style.FILL }
            canvas.drawCircle((intrinsicWidth / 4).toFloat(), (intrinsicHeight / 4).toFloat(), indicatorRadius, paint)
            paint.apply { style = Paint.Style.STROKE }
        }

    }

    fun showNotification(show: Boolean) {
        showNotification = show
        invalidateSelf()
    }

}
