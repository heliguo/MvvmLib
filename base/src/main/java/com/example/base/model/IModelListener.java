package com.example.base.model;

/**
 * @author:lgh on 2020/6/13 15:51
 * 通用model
 */
public interface IModelListener<T> extends IBaseModelListener{

    /**
     * 数据加载完成
     */
    void onLoadFinish(BaseModel<T> model,T data);

    /**
     * 加载失败
     */
    void onLoadFailed(BaseModel<T> model,String msg);

}
