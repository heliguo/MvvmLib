package com.example.common.service.config;

/**
 * @author lgh on 2020/6/17 10:32
 * @description 各个组件需要暴露给外部的service名称 配置
 */
public class ServiceConfig {

    private static final String SERVICE = "/service";

    /**
     * 用户模块
     */
    public static class User {
        /**
         * 用户登录状态
         */
        public static final String LOGIN_SERVICE = SERVICE + "/Login";
    }

}
