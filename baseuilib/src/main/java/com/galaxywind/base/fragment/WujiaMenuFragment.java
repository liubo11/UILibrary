package com.galaxywind.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.galaxywind.base.R;
import com.galaxywind.base.fragment.impl.IMoreMenu;
import com.galaxywind.base.ui.BaseFragment;
import com.galaxywind.utils.ColorUtils;
import com.galaxywind.utils.Logger;

/**
 * Created by Administrator on 2016-07-11.
 */
public abstract class WujiaMenuFragment extends BaseFragment implements IMoreMenu {
    private DrawerLayout mDrawer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("More menu fragment created");
    }

    @Override
    protected void setContent() {
        setContentView(R.layout.layout_more_menu);
    }

    @Override
    protected void initView() {
        initDrawerStyle();
        mRootView.setBackgroundColor(ColorUtils.getRandomColor());
    }

    private void initDrawerStyle() {
        if (mContainer.getParent() instanceof DrawerLayout) {
            DrawerLayout.LayoutParams lp = (DrawerLayout.LayoutParams) mContainer.getLayoutParams();
            lp.gravity = Gravity.LEFT;
        }
    }

    @Override
    public void closeMenu() {
        if (isCreatedView()) {
            mDrawer.closeDrawers();
        }
    }

    @Override
    public void openMenu() {
        if (isCreatedView()) {
            mDrawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void bindDrawer(DrawerLayout drawer) {
        this.mDrawer = drawer;
    }

}
