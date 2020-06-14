package com.example.base.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.ref.WeakReference;

/**
 * author:lgh on 2020/6/13 15:53
 * <p>
 * 通用基类model
 */
public abstract class BaseModel<T> extends SuperBaseModel<T> {

    /**
     * 数据加载成功通知所有注册者加载数据
     *
     * @param data 数据bean
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loadSuccess(T data) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakReference : mWeakReferenceDeque) {
                    if (weakReference.get() instanceof IModelListener) {
                        IModelListener iModelListener = (IModelListener) weakReference.get();
                        if (iModelListener != null) {
                            iModelListener.onLoadFinish(BaseModel.this, data);
                        }

                    }
                }
            }, 0);
        }
    }

    /**
     * 通知所有注册者加载失败
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loadFailed(String msg) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakReference : mWeakReferenceDeque) {
                    if (weakReference.get() instanceof IModelListener) {
                        IModelListener iModelListener = (IModelListener) weakReference.get();
                        if (iModelListener != null) {
                            iModelListener.onLoadFailed(BaseModel.this, msg);
                        }
                    }
                }

            }, 0);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void notifyCacheData(T data) {
        loadSuccess(data);
    }
}
