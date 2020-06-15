package com.example.base.loadsir;

import com.example.base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @author lgh
 * 类描述: 网络超时
 */
public class TimeoutCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.base_layout_timeout;
    }

}
