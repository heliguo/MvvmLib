package com.example.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * 应用模块: services
 * <p>
 * 类描述: app相关信息
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-01-27
 */
public interface IAppInfoService extends IProvider {

    String APP_INFO_SERVICE_NAME = "app_info_service";

    /**
     * 获取应用名称
     *
     * @return 应用名称
     */
    String getApplicationName();

    /**
     * 应用版本名
     *
     * @return 版本名
     */
    String getApplicationVersionName();

    /**
     * 应用版本号
     *
     * @return 版本号
     */
    String getApplicationVersionCode();

    /**
     * 应用程序是否debug
     *
     * @return boolean
     */
    boolean getApplicationDebug();

}
