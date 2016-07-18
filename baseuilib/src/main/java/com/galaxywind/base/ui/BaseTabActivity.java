/*
*Copyright@ GALAXYWIND Network Systems Co.Ltd.
*Function: BaseTabActivity
*Creator: LiuBo
*Create time: 2016-06-29
*/
package com.galaxywind.base.ui;

import android.support.annotation.LayoutRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.galaxywind.base.R;
import com.galaxywind.utils.Logger;
import com.galaxywind.utils.ViewUtils;

import java.util.List;


/**
 * @since 2016-06-29
 */
public abstract class BaseTabActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    protected TabLayout mTablayout;
    protected ViewPager mViewPager;

    @Override
    final protected void setContent() {
        setContentView();
    }

    final public void setContentView() {
        if (mTablayout != null) {
            Logger.i("Tab content has been set up");
            return;
        }
        super.setContentView(R.layout.layout_top_tab_page);

        mTablayout = (TabLayout) findViewById(R.id.base_tab);
        mViewPager = (ViewPager) findViewById(R.id.base_tab_container);
    }

    @Override
    final public void setContentView(@LayoutRes int layoutResID) {
        setContentView();
    }

    @Override
    final public void setContentView(View view) {
        setContentView();
    }

    @Override
    final public void setContentView(View view, ViewGroup.LayoutParams params) {
        setContentView();
    }

    final protected void setTabs(FragmentStatePagerAdapter adapter, List<TabLayout.Tab> tabs) {
        mViewPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.removeAllTabs();
        mTablayout.setSelectedTabIndicatorHeight(ViewUtils.dp2px(this, 2));

        for (int i = 0, j = tabs.size(); i < j; i++) {
            mTablayout.addTab(tabs.get(i), i);
        }

        mTablayout.setOnTabSelectedListener(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }
}
