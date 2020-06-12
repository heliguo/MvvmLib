package com.example.base.loadsir;

import com.example.base.R;
import com.kingja.loadsir.callback.Callback;

public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.base_layout_error;
    }
}
