package com.galaxywind.base.fragment.impl;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by Administrator on 2016-07-05.
 */
public interface ICtrlBar {
    void addLeftButton(@DrawableRes int drawable, View.OnClickListener listener);
    void addLeftTextButton(@StringRes int text, View.OnClickListener listener);
    void addBackButton();
    void addRightButton(@DrawableRes int drawable, View.OnClickListener listener);
    void addRightTextButton(@StringRes int text, View.OnClickListener listener);

    void clearLeftAdded();
    void clearRightAdded();

    void setTitle(CharSequence title);
    void setTitleColor(@ColorInt int color);

    /**
     * @param size px, def 16dp
     */
    void setTitleSize(int size);
    void setBarBackgroud(Drawable drawable);
    void setImmersedStyle(boolean immersedStyle);
}
