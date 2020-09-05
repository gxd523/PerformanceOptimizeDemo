package com.demo.optimize;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by guoxiaodong on 2020/9/4 16:39
 */
class Util {
    public static View generateView(Context context, View.OnClickListener onClickListener) {
        TextView textView = new TextView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(0xFFFF00FF);
        textView.setTextSize(30);
        textView.setLayoutParams(params);
        textView.setText(context.getClass().getSimpleName());
        textView.setOnClickListener(onClickListener);
        return textView;
    }
}
