package com.galaxywind.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;

import com.galaxywind.base.ui.BaseActivity;

/**
 * Created by Administrator on 2016-07-14.
 */
public class ThemeUtils {

    public static int getColor(@NonNull BaseActivity context, @AttrRes int color) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{color});
        int resColor = ta.getColor(0,0);
        ta.recycle();

        return resColor;
    }

    public static Drawable getDrawable(@NonNull BaseActivity context, @AttrRes int drawable) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{drawable});
        Drawable resDrawable = ta.getDrawable(0);
        ta.recycle();

        return resDrawable;
    }

    public static boolean getBoolean(@NonNull BaseActivity context, @AttrRes int bool) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{bool});
        boolean resBool = ta.getBoolean(0, false);
        ta.recycle();

        return resBool;
    }
}
