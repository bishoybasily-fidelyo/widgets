package com.fidelyo.widgets;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by bishoy on 2/22/18.
 */

public class UntouchableLayout extends RelativeLayout {

    public UntouchableLayout(Context context) {
        super(context);
    }

    public UntouchableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UntouchableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public UntouchableLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
