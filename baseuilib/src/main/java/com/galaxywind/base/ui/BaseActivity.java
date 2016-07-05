package com.galaxywind.base.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.galaxywind.base.R;
import com.galaxywind.base.adapter.LinearDecoration;
/**
 * Created by Administrator on 2016-06-29.
 */
public class BaseActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.layout_base_activity);
        ViewGroup viewGroup = null;//(ViewGroup) findViewById(R.id.container);
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
        mDrawer = (DrawerLayout) findViewById(R.id.base_drawer);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        bindLeftClick();
    }

    private void bindLeftClick() {
        setCBLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opposeLeftDrawer();
            }
        });
    }

    private void opposeLeftDrawer() {
        if (mDrawer != null) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawers();
            } else {
                mDrawer.openDrawer(GravityCompat.START);
            }
        }
    }

    protected void setCBLeftClickListener(View.OnClickListener listener) {
        findViewById(R.id.cb_back).setOnClickListener(listener);
    }

    protected void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }


    /*
     * after setContentView
     */
    protected void initMenuList(RecyclerView.Adapter adapter) {
        RecyclerView rView = (RecyclerView) findViewById(R.id.base_menu_list);
        LinearLayoutManager llManager = new LinearLayoutManager(this);

        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        llManager.setSmoothScrollbarEnabled(true);

        rView.setLayoutManager(llManager);
        rView.setHasFixedSize(true);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.addItemDecoration(new LinearDecoration(this, LinearDecoration.VERTICAL_LIST));
        rView.setAdapter(adapter);
    }

}
