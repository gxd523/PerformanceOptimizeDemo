package com.demo.optimize;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewTreeObserver;

import com.demo.optimize.databinding.ActivityMainBinding;

import androidx.databinding.DataBindingUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler handler = new Handler();
        for (int i = 0; i < 9999; i++) {
            handler.sendEmptyMessage(0);
        }
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
    }
}
