package com.example.common.config;

import com.example.base.base.BaseApplication;
import com.example.common.IModelInit;

/**
 * @author lgh on 2020/6/17 15:35
 * @description 组件生命周期初始化的配置类
 * 通过反射机制,动态调用每个组件初始化逻辑
 */
public class ModuleLifecycleConfig {

    private ModuleLifecycleConfig() {
    }

    private static class SingleHolder {
        private static ModuleLifecycleConfig INSTANCE = new ModuleLifecycleConfig();
    }

    public static ModuleLifecycleConfig getInstance() {
        return SingleHolder.INSTANCE;
    }

    public void initModuleAHead(BaseApplication application) {
        if (ModuleLifecycleReflexs.initAHeadModuleNames.length == 0) {
            return;
        }
        for (String initModuleName : ModuleLifecycleReflexs.initAHeadModuleNames) {
            try {
                Class<?> clazz = Class.forName(initModuleName);
                IModelInit iModelInit = (IModelInit) clazz.newInstance();
                iModelInit.onLoadAHead(application);
            } catch (ClassNotFoundException | IllegalAccessException |
                    InstantiationException e) {
                e.printStackTrace();
            }

        }
    }

    public void initModuleLow(BaseApplication application) {
        if (ModuleLifecycleReflexs.initLowModuleNames.length == 0) {
            return;
        }
        for (String initModuleName : ModuleLifecycleReflexs.initLowModuleNames) {
            try {
                Class<?> clazz = Class.forName(initModuleName);
                IModelInit iModelInit = (IModelInit) clazz.newInstance();
                iModelInit.onLoadLow(application);
            } catch (ClassNotFoundException | IllegalAccessException |
                    InstantiationException e) {
                e.printStackTrace();
            }

        }
    }

}
