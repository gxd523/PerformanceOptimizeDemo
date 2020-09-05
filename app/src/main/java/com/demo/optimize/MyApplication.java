package com.demo.optimize;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import androidx.multidex.MultiDex;

/**
 * Created by guoxiaodong on 2020-02-04 16:26
 */
public class MyApplication extends Application {
    public static Application instance;
    private final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private CountDownLatch latch = new CountDownLatch(2);

    /**
     * 应用所能接收到的最早的回调
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        String tracePath = getExternalCacheDir() + "/demo.trace";
//        Debug.startMethodTracing(tracePath);
//        TraceCompat.beginSection();
//        Trace.beginSection("AppOnCreate");
        LauncherTimer.startRecord(LauncherTimer.Tag.onPreDraw);
        LauncherTimer.startRecord(LauncherTimer.Tag.onWindowFocusChanged);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ExecutorService threadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);

        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                Log.d("gxd", "PerformanceApplication.run-->" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                Log.d("gxd", "PerformanceApplication.run-->" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                Log.d("gxd", "PerformanceApplication.run-->" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        try {
            TimeUnit.SECONDS.sleep(5);
            latch.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
