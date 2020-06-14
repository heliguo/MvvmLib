package com.example.base.livedatabus;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * author:lgh on 2020/6/13 17:13
 * <p>
 * 基于liveData的事件总线
 * 1.关联 activity / fragment 的生命周期 自动识别活动状态触发更新
 * 2.可以发送两种事件 普通事件 & 粘性事件
 * </p>
 */
public class LiveDataBus {

    /**
     * 粘性事件集合
     */
    private final Map<String, MutableLiveData> stickyBus;

    /**
     * 普通事件结合
     */
    private final Map<String, BusMutableLiveData> bus;

    public LiveDataBus() {
        stickyBus = new HashMap<>();
        bus = new HashMap<>();
    }

    private static class SingleHolder {
        private static final LiveDataBus LIVE_DATA_BUS = new LiveDataBus();
    }

    public LiveDataBus getInstance() {
        return SingleHolder.LIVE_DATA_BUS;
    }

    public MutableLiveData<Object> with(String key) {
        return with(key, Object.class);
    }

    private <T> MutableLiveData<T> with(String key, Class<T> objectClass) {
        if (!bus.containsKey(key)) {
            bus.put(key, new BusMutableLiveData());
        }
        return bus.get(key);
    }

    public MutableLiveData<Object> withSticky(String key) {

        return withSticky(key, Object.class);
    }

    private <T> MutableLiveData<T> withSticky(String key, Class<T> objectClass) {

        if (!stickyBus.containsKey(key)) {
            stickyBus.put(key, new MutableLiveData<T>());
        }

        return stickyBus.get(key);
    }


}
