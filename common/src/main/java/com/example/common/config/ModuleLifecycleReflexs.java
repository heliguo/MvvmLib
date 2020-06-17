package com.example.common.config;

/**
 * @author lgh on 2020/6/17 15:23
 * @description 组件生命周期初始化类的类目管理者,
 * 注册需要初始化的组件, 通过反射动态调用各个组件的初始化方法
 */
public class ModuleLifecycleReflexs {

    /**
     * 基础库
     */
    private static final String BASEINIT = "com.example.common.CommonModuleInit";

    /**
     * main组件库
     */
    private static final String MAININIT = "com.example.main.MainModuleInit";

    /**
     * 用户组件初始化
     */
    private static final String USERINIT = "com.example.user.UserModuleInit";

    /**
     * 先初始化
     */
    public static String[] initAHeadModuleNames = {BASEINIT, MAININIT, USERINIT};

    /**
     * 后初始化
     */
    public static String[] initLowModuleNames = {};

}
