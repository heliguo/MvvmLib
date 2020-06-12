package com.example.base.model;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.RequiresApi;

import com.example.base.livedatabus.IBaseModelListener;
import com.example.base.utils.GsonUtils;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentLinkedDeque;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * author:lgh on 2020/6/12 15:55
 * 数据类接口
 */
public abstract class SuperBaseModel<T> {

    protected Handler mUiHandler = new Handler(Looper.getMainLooper());

    protected CompositeDisposable mCompositeDisposable;

    /**
     * 引用队列
     */
    protected ReferenceQueue<IBaseModelListener> mQueue;

    /**
     * 线程安全队列
     */
    protected ConcurrentLinkedDeque<WeakReference<IBaseModelListener>> mWeakReferenceDeque;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SuperBaseModel() {
        mQueue = new ReferenceQueue<>();
        mWeakReferenceDeque = new ConcurrentLinkedDeque<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void register(IBaseModelListener listener) {
        if (listener == null) {
            return;
        }
        synchronized (this) {
            Reference<? extends IBaseModelListener> poll;
            while ((poll = mQueue.poll()) != null) {
                mWeakReferenceDeque.remove(poll);
            }
            for (WeakReference<IBaseModelListener> reference : mWeakReferenceDeque) {
                IBaseModelListener iBaseModelListener = reference.get();
                if (iBaseModelListener == listener) {
                    return;
                }
            }
            WeakReference<IBaseModelListener> reference = new WeakReference<>(listener, mQueue);
            mWeakReferenceDeque.add(reference);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void unRegister(IBaseModelListener listener) {

        if (listener == null) {
            return;
        }

        synchronized (this) {
            for (WeakReference<IBaseModelListener> reference : mWeakReferenceDeque) {
                IBaseModelListener iBaseModelListener = reference.get();
                if (iBaseModelListener == listener) {
                    mWeakReferenceDeque.remove(listener);
                    break;
                }
            }
        }

    }

    /**
     * 需要缓存的类
     */
    protected Type getTClass() {
        return null;
    }

    /**
     * 该model的数据是否有apk预制的数据,如果有请返回
     */
    protected String getApkCache() {
        return null;
    }

    /**
     * 是否需要更新数据,默认每一次都需要更新
     *
     * @return true
     */
    protected boolean isNeedToUpData() {
        return true;
    }

    /**
     * 获取缓存数据并加载网络数据
     */
    public void getCacheDataAndLoad() {
        if (getApkCache() != null) {
            notifyCacheData(GsonUtils.fromLocalJson(getApkCache(), getTClass()));
            if (isNeedToUpData()) {
                load();
                return;
            }
        }
        //不需要加载本地数据直接获取网络数据
        load();
    }

    /**
     * 加载缓存数据
     */
    protected abstract void notifyCacheData(T data);

    /**
     * 加载网络数据
     */
    protected abstract void load();

    /**
     * 添加订阅
     */
    public void addDisposable(Disposable disposable) {
        if (disposable == null) {
            return;
        }

        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消所有订阅
     */
    public void cancel() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

}
