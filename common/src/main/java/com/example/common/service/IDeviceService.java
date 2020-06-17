package com.example.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @author lgh
 * <p>
 * 类描述: 与设备相关的配置信息
 * <p>
 */
interface IDeviceService extends IProvider {

    String DEVICE_SERVICE_NAME = "device_name";

    /**
     * 获取设备默认语言
     *
     * @return 语言
     */
    String getDeviceDefaultLanguage();

    /**
     * 获取ClientId
     *
     * @return ClientId
     */
    String getClientId();

    /**
     * 获取当前设备安卓系统版本号
     *
     * @return 系统版本号
     */
    String getSystemVersion();

    /**
     * 获取手机品牌
     *
     * @return 品牌
     */
    String getPhoneBrand();

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return API
     */
    int getBuildLevel();

    /**
     * 获取设备宽度（px）
     *
     * @return 设备宽度（px）
     */
    int getDeviceWidth();

    /**
     * 获取设备高度（px）
     *
     * @return 设备高度（px）
     */
    int getDeviceHeight();

    /**
     * SD卡判断
     *
     * @return 是否有sd卡
     */
    boolean isSDCardAvailable();

    /**
     * 是否有网
     *
     * @return network
     */
    boolean isNetworkConnected();

    /**
     * 是否这次安装时新安装
     *
     * @return first install
     */
    boolean isNewInstall();

}
