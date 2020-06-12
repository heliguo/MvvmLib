package com.example.base.activity;

/**
 * author:lgh on 2020/6/12 10:21
 * 页面加载
 */
public interface IBaseView {

    /**
     * 显示内容
     */
    void showContent();

    /**
     * 显示加载提示
     */
    void showLoading();

    /**
     * 显示空页面
     */
    void showEmpty();

    /**
     * 显示失败
     */
    void showFailure(String message);


}
