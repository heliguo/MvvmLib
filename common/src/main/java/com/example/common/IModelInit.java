package com.example.common;

import com.example.base.base.BaseApplication;

/**
 * @author:lgh on 2020/6/14 21:02
 */
public interface IModelInit {

    /**
     * 必须初始化的
     *
     * @param baseApplaction base
     * @return boolean
     */
    boolean onLoadAHead(BaseApplication baseApplaction);

    /**
     * 按需初始化
     *
     * @param baseApplaction base
     * @return boolean
     */
    boolean onLoadLow(BaseApplication baseApplaction);


}
