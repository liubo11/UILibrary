package com.galaxywind.base.fragment.impl;

import android.support.v4.widget.DrawerLayout;

/**
 * Created by Administrator on 2016-07-05.
 */
public interface IMoreMenu {
    void closeMenu();
    void openMenu();

    void bindDrawer(DrawerLayout drawer);
}
