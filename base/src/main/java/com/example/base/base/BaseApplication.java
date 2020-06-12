package com.example.base.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * author:lgh on 2020/6/12 13:26
 */
public class BaseApplication extends Application {

    private static boolean sDebug;

    private static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
    }

    private void setApplication(BaseApplication baseApplication) {
        sInstance = baseApplication;
        baseApplication.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                AppManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                AppManager.getInstance().removeActivity(activity);
            }
        });
    }

    /**
     * 获得当前app运行的Application
     */
    public static BaseApplication getInstance() {
        if (sInstance == null) {
            throw new NullPointerException(
                    "please inherit BaseApplication or call setApplication.");
        }
        return sInstance;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void setDebug(boolean debug) {
        sDebug = debug;
    }

    /**
     * 获取当前进程名
     */
    public static String getCurrentProcessName(Context context) {

        ActivityManager am = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> rap = am.getRunningAppProcesses();

        if (rap == null || rap.size() == 0) {
            return null;
        }

        for (ActivityManager.RunningAppProcessInfo processInfo : rap) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }

}
