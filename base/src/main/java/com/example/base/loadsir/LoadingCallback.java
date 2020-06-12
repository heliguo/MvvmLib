package com.example.base.loadsir;

import android.content.Context;
import android.view.View;

import com.example.base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * author:lgh on 2020/6/12 11:20
 */
public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.base_layout_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
