package com.example.base.model;

/**
 * author:lgh on 2020/6/13 16:16
 */
public interface IPagingModelListener<T> extends IBaseModelListener {

    /**
     * 数据加载完成
     */
    void onLoadFinish(BasePagingModel<T> model, T data, boolean isEmpty, boolean isFirstPage);

    /**
     * 加载失败
     */
    void onLoadFailed(BasePagingModel<T> model, String msg, boolean isFirstPage);

}
