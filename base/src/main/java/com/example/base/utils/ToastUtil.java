package com.example.base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtil {

    private static Toast mToast;

    public static void show(Context context, String msg) {
        try {
            if (null != context && !TextUtils.isEmpty(msg)) {
                if (null != mToast) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                mToast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
