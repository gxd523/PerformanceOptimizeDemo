package com.demo.optimize;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * Created by guoxiaodong on 2020-02-04 16:26
 */
public class PerformanceApplication extends Application {
    public static Application instance;

    /**
     * 应用所能接收到的最早的回调
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LauncherTimer.startRecord(LauncherTimer.Tag.onPreDraw);
        LauncherTimer.startRecord(LauncherTimer.Tag.onWindowFocusChanged);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
}
