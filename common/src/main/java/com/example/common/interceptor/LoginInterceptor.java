package com.example.common.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.utils.LogUtils;
import com.example.common.router.RouterActivityPath;
import com.example.common.service.ILoginService;

/**
 * @author lgh on 2020/6/17 9:59
 * @description 在跳转过程中处理登陆事件，不需要在目标页重复做登陆检查
 * 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
 */
@Interceptor(priority = 8)
public class LoginInterceptor implements IInterceptor {

    private Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getPath().equals(RouterActivityPath.User.PAGER_LOGIN)) {
            ILoginService loginService = ARouter.getInstance().navigation(ILoginService.class);
            if (loginService.isLogin()) {
                //处理完成，交还控制权
                callback.onContinue(postcard);
                // 觉得有问题，中断路由流程
                // callback.onInterrupt(new RuntimeException("我觉得有点异常"));
                // 以上两种至少需要调用其中一种，否则不会继续路由
            } else {
                ARouter.getInstance().build(RouterActivityPath.User.PAGER_LOGIN)
                        .greenChannel().navigation(mContext);
                callback.onInterrupt(null);
            }
        } else {
            callback.onContinue(postcard);
        }


    }

    @Override
    public void init(Context context) {
        mContext = context;
        LogUtils.e(this, "login intercepted");
    }
}
