package com.fidelyo.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bishoy on 1/7/18.
 */

public class NestedSeekBar extends AppCompatSeekBar {

    public NestedSeekBar(Context context) {
        super(context);
        initialize();
    }

    public NestedSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public NestedSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
    }
}
