package com.galaxywind.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galaxywind.base.R;
import com.galaxywind.base.fragment.impl.IMoreMenu;
import com.galaxywind.base.ui.BaseFragment;
import com.galaxywind.utils.ColorUtils;
import com.galaxywind.utils.Logger;

/**
 * Created by Administrator on 2016-07-05.
 */
public class LeftMenuFragment extends BaseFragment implements IMoreMenu {

    private ViewGroup mContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Logger.d("More menu fragment create view");
        mContainer = container;
        initDrawerStyle();

        View view = inflater.inflate(R.layout.layout_more_menu, container, false);
        view.setBackgroundColor(ColorUtils.getRandomColor());

        return view;
    }

    private void initDrawerStyle() {
        if (mContainer.getParent() instanceof DrawerLayout) {
            DrawerLayout.LayoutParams lp = (DrawerLayout.LayoutParams) mContainer.getLayoutParams();
            lp.gravity = Gravity.LEFT;
        }
    }

    private DrawerLayout mDrawer;

    private void opposeLeftDrawer() {
        if (mDrawer != null) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawers();
            } else {
                mDrawer.openDrawer(GravityCompat.START);
            }
        }
    }

    @Override
    public void closeMenu() {

    }

    @Override
    public void openMenu() {

    }

    @Override
    public void bindDrawer(DrawerLayout drawer) {

    }
}
