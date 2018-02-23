package com.fidelyo.widgets;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by bishoy on 2/16/17.
 */

public class DatePickerView extends DatePicker {

    public DatePickerView(Context context) {
        super(context);
        initialize();
    }

    public DatePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {

    }

    public final Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, getYear());
        calendar.set(Calendar.MONTH, getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, getDayOfMonth());
        return calendar.getTime();
    }

    public final void setDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
    }
}
