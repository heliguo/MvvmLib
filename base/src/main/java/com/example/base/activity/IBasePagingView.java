package com.example.base.activity;

/**
 * @author:lgh on 2020/6/12 10:22
 * 重新加载
 */
public interface IBasePagingView extends IBaseView {

    /**
     * 加载更多失败
     */
    void onLoadMoreFailure(String message);

    /**
     * 没有更多了
     */
    void onLoadMoreEmpty();

}
