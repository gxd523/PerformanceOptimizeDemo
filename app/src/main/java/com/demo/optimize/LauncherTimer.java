package com.demo.optimize;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guoxiaodong on 2020-02-04 16:37
 */
public class LauncherTimer {
    private static Map<Tag, Long> startTimeMap = new HashMap<>();

    public static void startRecord(Tag tag) {
        if (!startTimeMap.containsKey(tag)) {
            startTimeMap.put(tag, System.currentTimeMillis());
        }
    }

    public static void stopRecord(Tag tag) {
        if (startTimeMap.containsKey(tag)) {
            Long startTime = startTimeMap.get(tag);
            if (startTime != null) {
                long duration = System.currentTimeMillis() - startTime;
                Log.d("gxd", tag + "-->" + duration);
            }
            startTimeMap.remove(tag);
        }
    }

    public enum Tag {
        onPreDraw,
        onWindowFocusChanged,
    }
}
