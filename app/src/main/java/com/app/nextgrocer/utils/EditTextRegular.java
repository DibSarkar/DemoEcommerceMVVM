package com.app.nextgrocer.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class EditTextRegular extends AppCompatEditText
    {
    public EditTextRegular(Context context) {
        super(context);
        setTypeface(CommonUtils.getTypefaceRegular(getContext()));
    }

    public EditTextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(CommonUtils.getTypefaceRegular(getContext()));
    }

    public EditTextRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(CommonUtils.getTypefaceRegular(getContext()));
    }

}
