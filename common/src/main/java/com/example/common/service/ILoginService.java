package com.example.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.example.common.contract.UserInfo;

/**
 * @author lgh on 2020/6/17 10:04
 * @description app 登录信息
 */
public interface ILoginService extends IProvider {

    /**
     * 登录
     */
    void login();

    /**
     * 管理员身份登录
     *
     * @param isMainAccountLogin boolean
     */
    void login(boolean isMainAccountLogin);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 取消登录
     */
    void onLoginCancel();

    /**
     * 保存登录状态
     *
     * @param status boolean
     */
    void saveStatus(boolean status);

    /**
     * 是否登录
     *
     * @return boolean
     */
    boolean isLogin();

    /**
     * 获取 token
     *
     * @return token
     */
    String getToken();

    /**
     * token过期处理
     */
    void onTokenExpire();

    /**
     * 获取 UUID
     *
     * @return UUID
     */
    String getUUID();

    /**
     * 刷新用户登录信息
     */
    void refreshUserInfo();

    /**
     * 获取用户信息
     *
     * @return UserInfo
     */
    UserInfo getUserInfo();

}
