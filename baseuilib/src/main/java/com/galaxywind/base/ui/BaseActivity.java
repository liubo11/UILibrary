package com.galaxywind.base.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.galaxywind.base.R;
import com.galaxywind.base.fragment.FragmentFactory;
import com.galaxywind.base.fragment.impl.ICtrlBar;
import com.galaxywind.base.fragment.impl.IMoreMenu;

/**
 * Created by Administrator on 2016-06-29.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String FM_TAG_CONTROL_BAR = "com.base.baseactivity.contrlbar";
    private static final String FM_TAG_MORE_MEMU = "com.base.baseacitivity.moremenu";

    protected ICtrlBar mCtrlBar;
    protected IMoreMenu mMoreMenu;

    protected Bundle mSaveInstacneState;

    public enum MenuStyle {
        LEFT,
        RIGHT
    }
    private MenuStyle mMenuStyle = MenuStyle.LEFT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (null != savedInstanceState) {
            mSaveInstacneState = new Bundle(savedInstanceState);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.layout_base_activity);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        setContent_(LayoutInflater.from(this).inflate(layoutResID, viewGroup, false), null);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.layout_base_activity);
        setContent_(view, null);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(R.layout.layout_base_activity);
        setContent_(view, params);
    }

    private void setContent_(View view, ViewGroup.LayoutParams params) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        if (null != params) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(params);
            viewGroup.addView(view, lp);
        } else {
            viewGroup.addView(view);
        }

        FragmentManager fm = getSupportFragmentManager();
        if (null == mSaveInstacneState) {
            FragmentTransaction ft = fm.beginTransaction();

            BaseFragment ctrlBar = createControlBar();
            BaseFragment moreMenu = createMoreMenu();

            ft.add(R.id.frame_ctrl_bar, ctrlBar, FM_TAG_CONTROL_BAR);
            ft.add(R.id.frame_more_menu, moreMenu, FM_TAG_MORE_MEMU);

            mCtrlBar = (ICtrlBar) ctrlBar;
            mMoreMenu = (IMoreMenu) moreMenu;
            ft.commit();
        } else {
            mCtrlBar = (ICtrlBar) fm.findFragmentByTag(FM_TAG_CONTROL_BAR);
            mMoreMenu = (IMoreMenu) fm.findFragmentByTag(FM_TAG_MORE_MEMU);
        }

        mMoreMenu.bindDrawer((DrawerLayout) findViewById(R.id.base_drawer));
    }

    protected void setMenuStyle(MenuStyle menuStyle) {
        this.mMenuStyle = menuStyle;
    }

    protected BaseFragment createControlBar() {
        return FragmentFactory.newControlBar();
    }

    protected BaseFragment createMoreMenu() {
        return FragmentFactory.newMoreMenu(mMenuStyle);
    }

    public ICtrlBar getControlBar() {
        return mCtrlBar;
    }
    public IMoreMenu getMoreMenu() {
        return mMoreMenu;
    }

    protected void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }
}
