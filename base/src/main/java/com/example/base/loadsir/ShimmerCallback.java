package com.example.base.loadsir;

import android.content.Context;
import android.view.View;

import com.example.base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @author lgh
 * 类描述: 骨架屏
 */
public class ShimmerCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.base_layout_placeholder;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
