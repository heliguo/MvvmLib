package com.example.base.livedatabus;

import androidx.lifecycle.Observer;

/**
 * author:lgh on 2020/6/13 17:55
 * observer 包装类
 */
public class ObserverWrapper<T> implements Observer<T> {

    private Observer<T> observer;

    public ObserverWrapper(Observer<T> observer) {
        this.observer = observer;
    }

    @Override
    public void onChanged(T t) {
        if (observer != null) {
            if (isCallOnObserve()) {
                return;
            }
            observer.onChanged(t);
        }
    }

    private boolean isCallOnObserve() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (StackTraceElement traceElement : stackTrace) {
                if (("android.arch.lifecycle.LiveData".equals(traceElement.getClassName()) ||
                        "androidx.lifecycle.LiveData".equals(traceElement.getClassName())) &&
                        "observeForever".equals(traceElement.getMethodName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
