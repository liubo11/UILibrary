package com.galaxywind.base.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.galaxywind.base.R;

import java.util.List;


/**
 * Created by Administrator on 2016-06-29.
 */
public class BaseTabActivity extends BaseActivity {

    protected TabLayout mTablayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();


        TabLayout.Tab tabs = null;

    }

    public void setContentView() {
        super.setContentView(R.layout.layout_top_tab_page);

        mTablayout = (TabLayout) findViewById(R.id.base_tab);
        mViewPager = (ViewPager) findViewById(R.id.base_tab_container);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView();
    }

    @Override
    public void setContentView(View view) {
        setContentView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        setContentView();
    }

    protected void initTab(FragmentStatePagerAdapter adapter, List<TabLayout.Tab> tabs) {
        mViewPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.removeAllTabs();
        mTablayout.setSelectedTabIndicatorHeight(0);

        for (int i = 0, j = tabs.size(); i < j; i++) {
            mTablayout.addTab(tabs.get(i), i);
        }
    }
}
