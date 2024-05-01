package com.example.newspaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class forfont extends TextView {
    Context context;

    public forfont(Context context) {
        super(context);
        this.context = context;
        customtext();
    }

    public forfont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        customtext();
    }

    public void customtext() {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "DancingScript-Regular.ttf");
        this.setTypeface(typeface);
    }
}
