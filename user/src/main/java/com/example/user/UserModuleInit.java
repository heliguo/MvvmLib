package com.example.user;

import com.example.base.base.BaseApplication;
import com.example.common.IModelInit;

/**
 * @author lgh on 2020/6/17 15:33
 * @description user model application
 */
public class UserModuleInit implements IModelInit {
    @Override
    public boolean onLoadAHead(BaseApplication baseApplaction) {
        return false;
    }

    @Override
    public boolean onLoadLow(BaseApplication baseApplaction) {
        return false;
    }
}
