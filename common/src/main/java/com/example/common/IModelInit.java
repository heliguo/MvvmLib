package com.example.common;

/**
 * author:lgh on 2020/6/14 21:02
 */
public interface IModelInit {

    /**
     * 必须初始化的
     *
     * @param baseApplaction base
     * @return boolean
     */
    boolean onLoadAHead(BaseApplaction baseApplaction);

    /**
     * 按需初始化
     *
     * @param baseApplaction base
     * @return boolean
     */
    boolean onLoadLow(BaseApplaction baseApplaction);


}
