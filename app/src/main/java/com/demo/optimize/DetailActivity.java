package com.demo.optimize;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by guoxiaodong on 2020/9/4 16:33
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Util.generateView(this, null));
    }
}
