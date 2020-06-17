package com.example.common;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.base.BaseApplication;
import com.tencent.mmkv.MMKV;

/**
 * @author lgh on 2020/6/17 9:21
 * @description 通用库 & 基础库 自身初始化操作
 */
public class CommonModuleInit implements IModelInit {

    @Override
    public boolean onLoadAHead(BaseApplication baseApplaction) {

        if (baseApplaction.isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        MMKV.initialize(baseApplaction);
        ARouter.init(baseApplaction);

        return false;
    }

    @Override
    public boolean onLoadLow(BaseApplication baseApplaction) {
        return false;
    }
}
