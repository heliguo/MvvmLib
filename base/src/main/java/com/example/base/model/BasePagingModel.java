package com.example.base.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.ref.WeakReference;

/**
 * author:lgh on 2020/6/13 16:17
 */
public abstract class BasePagingModel<T> extends SuperBaseModel<T> {


    protected String nextPageUrl = "";

    /**
     * 是否下拉刷新 or 上拉加载
     */
    protected boolean isRefresh = true;

    /**
     * 加载成功
     * @param data 数据bean
     * @param isEmpty 是否为空
     * @param isFirstPage 是否是第一页
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loadFinish(T data, boolean isEmpty, boolean isFirstPage) {

        synchronized (this){
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakReference : mWeakReferenceDeque) {
                    if (weakReference.get() instanceof IPagingModelListener){
                        IPagingModelListener iPagingModelListener = (IPagingModelListener) weakReference.get();
                        if (iPagingModelListener!=null){
                            iPagingModelListener.onLoadFinish(BasePagingModel.this,data,isEmpty,isFirstPage);
                        }
                    }
                }
            },0);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loadFailed(String msg, boolean isFirstPage){
        synchronized (this){
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakReference : mWeakReferenceDeque) {
                    if (weakReference.get() instanceof IPagingModelListener){
                        IPagingModelListener iPagingModelListener = (IPagingModelListener) weakReference.get();
                        if (iPagingModelListener!=null){
                            iPagingModelListener.onLoadFailed(BasePagingModel.this,msg,isFirstPage);
                        }
                    }
                }
            },0);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void notifyCacheData(T data) {
        loadFinish(data,false,true);
    }
}
