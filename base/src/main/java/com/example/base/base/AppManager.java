package com.example.base.base;

import android.app.Activity;

import java.util.Stack;

/**
 * @author:lgh on 2020/6/12 13:29
 * <p>
 * activity管理类 静态内部类单例模式
 * </p>
 */
public class AppManager {

    private static Stack<Activity> sActivityStack;

    private AppManager() {
    }

    private static class ManagerProvider {
        private static AppManager INSTANCE = new AppManager();
    }

    public static AppManager getInstance() {
        return ManagerProvider.INSTANCE;
    }


    /**
     * 添加指定activity
     *
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<>();
        }
        sActivityStack.add(activity);
    }

    /**
     * 移除指定activity
     */
    public void removeActivity(Activity activity) {
        sActivityStack.remove(activity);
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return sActivityStack.empty();//为空true，不空false
    }

    /**
     * 获取栈顶activity
     */
    public Activity getCurrentActivity() {
        return sActivityStack.lastElement();
    }

    /**
     * 移除栈顶activity
     */
    public void removeCurrentActivity() {
        finishActivity(sActivityStack.lastElement());
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 结束指定类名的activity
     */
    public void finishActivityByCls(Class<?> clazz) {
        for (Activity activity : sActivityStack) {
            if (activity.getClass().equals(clazz)) {
                finishActivity(activity);
                break;
            }
        }

    }

    /**
     * 获取指定类名的activity
     */
    public Activity getActivityByCls(Class<?> clazz) {

        for (Activity activity : sActivityStack) {
            if (activity.getClass().equals(clazz)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 移除所有activity
     */
    public void finishAllActivity() {
        for (Activity activity : sActivityStack) {
            finishActivity(activity);
        }
        sActivityStack.clear();
    }

    /**
     * 退出程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
        } catch (Exception e) {
            sActivityStack.clear();
            e.printStackTrace();
        }

    }

}
