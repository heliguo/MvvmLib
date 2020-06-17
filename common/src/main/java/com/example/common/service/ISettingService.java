package com.example.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @author lgh
 * <p>
 * 类描述: 与app设置相关
 * <p>
 */
public interface ISettingService extends IProvider {

    String SETTINGS_SERVICE_NAME = "settings_service";

    /**
     * 语言
     **/
    int CODE_LANGUAGE = 1;

    /**
     * 主题
     */
    int CODE_THEME = 2;

    /**
     * 字体
     */
    int CODE_FONT_SCHEME = 3;

    /**
     * 获取主题
     *
     * @return 0:暗色  1.亮色  2.纯黑
     */
    int getThemeValue();

    /**
     * 设置主题
     *
     * @param theme theme
     */
    void setThemeValue(String theme);


}
