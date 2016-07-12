package com.galaxywind.utils;

import android.os.Build;
import android.os.SystemClock;

/**
 * Created by Administrator on 2016-07-11.
 */
public class AppTimeUtils {
    public static final long SF_ACTIVITY_START_SPACE = 2000l;


    // 仅用于Activity跳转
    private static long lastRecordTime = 0l;
    /**
     * @return true 可跳转
     */
    public static boolean isValidStartActivityAction() {
        long n = SystemClock.uptimeMillis();
        if (n != 0l) {
            if (Math.abs(lastRecordTime - n) < SF_ACTIVITY_START_SPACE) {
                return  false;
            }
            lastRecordTime = n;
        }
        return true;
    }
}
