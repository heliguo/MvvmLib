package com.example.common.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author lgh on 2020/6/17 11:11
 * @description 定义字体
 */
public class CommonCustomTextView extends AppCompatTextView {

    public CommonCustomTextView(Context context) {
        this(context, null);
    }

    public CommonCustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonCustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 定制字体
     */
    private void init(Context context) {
        AssetManager am = context.getAssets();
        Typeface typeface = Typeface.createFromAsset(am, "fonts/Lobster-1.4.otf");
        setTypeface(typeface);
    }

}
