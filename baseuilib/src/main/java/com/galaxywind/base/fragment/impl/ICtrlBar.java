package com.galaxywind.base.fragment.impl;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016-07-05.
 */
public interface ICtrlBar {
    void addLeftButton();
    void addLeftTextButton();
    void addLeftDefButton();
    void addRightButton();
    void addRightTextButton();

    void clearLeftAdded();
    void clearRightAdded();

    void setTitle(CharSequence title);
    void setTitleColor(int color);
    void setTitleSize(int size);
    void setBarBackgroud(Drawable drawable);
    void setImmersedStyle(boolean immersedStyle);
}
