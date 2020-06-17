package com.example.common.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.example.base.utils.GsonUtils;

import java.lang.reflect.Type;

/**
 * @author lgh on 2020/6/17 9:34
 * 参阅：https://github.com/alibaba/ARouter/blob/master/README_CN.md
 * @description 传递自定义对象，新建一个类（并非自定义对象类）
 * 然后实现 SerializationService,并使用@Route注解标注(方便用户自行选择序列化方式)
 */
@Route(path = "/vido/json")
public class JsonServiceImpl implements SerializationService {
    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return GsonUtils.fromLocalJson(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return GsonUtils.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return GsonUtils.fromLocalJson(input, clazz);
    }

    @Override
    public void init(Context context) {

    }
}
