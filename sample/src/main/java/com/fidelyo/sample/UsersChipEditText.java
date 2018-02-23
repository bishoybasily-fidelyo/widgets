package com.fidelyo.sample;

import android.content.Context;
import android.util.AttributeSet;

import com.fidelyo.widgets.ChipEditText;

/**
 * Created by bishoy on 2/23/18.
 */

public class UsersChipEditText extends ChipEditText<User> {

    public UsersChipEditText(Context context) {
        super(context);
    }

    public UsersChipEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UsersChipEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
