package com.fidelyo.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bishoy on 2/23/18.
 */

public class ChipEditText<T extends Chip> extends AppCompatEditText implements TextWatcher {

    private final List<T> chips = new ArrayList<>();
    private final List<T> chipsToRemove = new ArrayList<>();

    private ChipCallback chipCallback = text -> Log.i("ChipEditText", text);

    private View chipView;

    public ChipEditText(Context context) {
        super(context);
        initialize();
    }

    public ChipEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ChipEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        chipView = LayoutInflater.from(getContext()).inflate(R.layout.item_chip, null);

        updateText();
    }

    public void addChip(T chip) {
        chips.add(chip);
        updateText();
    }

    public void removeChip(Chip chip) {
        chips.remove(chip);
        updateText();
    }

    private void updateText() {

        removeTextChangedListener(this);

        String[] tokens = new String[chips.size()];
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = chips.get(i).text();
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(TextUtils.join("", tokens));

        Integer index = 0;
        for (Chip chip : chips) {

            chip.index(index);

            TextView textView = chipView.findViewById(R.id.text);
            textView.setText(chip.text());
            BitmapDrawable drawable = new BitmapDrawable(captureView(textView));
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            builder.setSpan(new ImageSpan(drawable), index, index + chip.text().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            index += chip.text().length();

        }

        setText(builder);

        setSelection(builder.length());

        addTextChangedListener(this);

    }

    private Bitmap captureView(View view) {

        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(measureSpec, measureSpec);

        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();

        view.layout(0, 0, measuredWidth, measuredHeight);

        Bitmap bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        if (count > 0 && after < count) {
            int end = start + count;

            for (T chip : chips) {
                if (chip.index() == start && chip.index() + chip.text().length() == end) {
                    chipsToRemove.add(chip);
                }
            }

        }

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (!chipsToRemove.isEmpty()) {
            chips.removeAll(chipsToRemove);
            chipsToRemove.clear();

            updateText();
        }

        chipCallback.onSearch(getRemaining(editable));

    }

    public void handleSearch(ChipCallback chipCallback) {
        this.chipCallback = chipCallback;
    }

    @NonNull
    private String getRemaining(Editable editable) {
        StringBuilder stringBuilder = new StringBuilder(editable);

        int repSt, repEn, repTot = 0;

        for (Chip chip : chips) {

            repSt = chip.index();
            repEn = chip.index() + chip.text().length();

            repSt = repSt - repTot;
            repEn = repEn - repTot;

            stringBuilder.replace(repSt, repEn, "");

            repTot += (repEn - repSt);

        }

        return stringBuilder.toString();
    }

    public List<T> getItems() {
        return chips;
    }
}