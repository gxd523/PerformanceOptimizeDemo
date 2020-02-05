package com.demo.optimize;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import com.demo.optimize.databinding.ActivityMainBinding;

import androidx.databinding.DataBindingUtil;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        super.onCreate(savedInstanceState);
        final ActivityMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataBinding.titleTv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                dataBinding.titleTv.getViewTreeObserver().removeOnPreDrawListener(this);
                LauncherTimer.stopRecord(LauncherTimer.Tag.onPreDraw);
                return true;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LauncherTimer.stopRecord(LauncherTimer.Tag.onWindowFocusChanged);
//        Debug.stopMethodTracing();
//        Trace.endSection();
    }
}
