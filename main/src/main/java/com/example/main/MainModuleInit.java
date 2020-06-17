package com.example.main;

import com.example.base.base.BaseApplication;
import com.example.common.IModelInit;

/**
 * @author lgh on 2020/6/17 15:26
 * @description main model application
 */
public class MainModuleInit implements IModelInit {
    @Override
    public boolean onLoadAHead(BaseApplication baseApplaction) {
        return false;
    }

    @Override
    public boolean onLoadLow(BaseApplication baseApplaction) {
        return false;
    }
}
