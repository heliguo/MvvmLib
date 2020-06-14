package com.example.base.livedatabus;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * author:lgh on 2020/6/13 17:16
 * <p>
 * 扩展 livedata , hook 源码 拦截 实现非粘性事件
 * Mutable 可变的
 * </p>
 */
public class BusMutableLiveData<T> extends MutableLiveData<T> {

    private Map<Observer, Observer> mObserverMap = new HashMap<>();

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
        try {
            hook(observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        if (!mObserverMap.containsKey(observer)) {
            mObserverMap.put(observer, new ObserverWrapper(observer));
        }
        super.observeForever(mObserverMap.get(observer));
    }

    @Override
    public void removeObserver(@NonNull Observer<? super T> observer) {
        Observer observer1;
        if (mObserverMap.containsKey(observer)) {
            observer1 = mObserverMap.get(observer);
        } else {
            observer1 = observer;
        }
        super.removeObserver(observer1);
    }

    /**
     * hook源码实现,拦截订阅之前的事件
     * @param observer observer
     * @throws Exception e
     */
    private void hook(Observer<? super T> observer) throws Exception {

        Class<LiveData> liveDataClass = LiveData.class;
        Field safeIterableMap = liveDataClass.getDeclaredField("mObservers");
        safeIterableMap.setAccessible(true);
        Object objectObservers = safeIterableMap.get(this);
        Class<?> observersClass = objectObservers.getClass();
        Method methodGet = observersClass.getDeclaredMethod("get", Object.class);
        methodGet.setAccessible(true);
        Object objectWrapperEntry = methodGet.invoke(objectObservers, observer);
        Object objectWrapper = null;
        if (objectWrapperEntry instanceof Map.Entry) {
            objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
        }
        if (objectWrapper == null) {
            throw new NullPointerException("Wrapper can not be bull!");
        }
        Class<?> classObserverWrapper = objectWrapper.getClass().getSuperclass();
        Field fieldLastVersion = classObserverWrapper.getDeclaredField("mLastVersion");
        fieldLastVersion.setAccessible(true);
        //get livedata's version
        Field fieldVersion = liveDataClass.getDeclaredField("mVersion");
        fieldVersion.setAccessible(true);
        Object objectVersion = fieldVersion.get(this);
        //set wrapper's version
        fieldLastVersion.set(objectWrapper, objectVersion);

    }

}
