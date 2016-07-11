package com.demo.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.galaxywind.base.adapter.SimpleTabAdapter;
import com.galaxywind.base.fragment.FragmentFactory;
import com.galaxywind.base.ui.BaseTabActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-07-01.
 */
public class TabActivity extends BaseTabActivity {
    @Override
    protected void initField() {
        setMenuStyle(MenuStyle.NONE);
    }

    @Override
    protected void initView() {
        ArrayList<TestTabFragment> fmList = new ArrayList<>();

        TestTabFragment ttf = FragmentFactory.newFragmentInstance(TestTabFragment.class, new Bundle());
        ttf.setText("First Tab");
        fmList.add(ttf);

        ttf = FragmentFactory.newFragmentInstance(TestTabFragment.class, new Bundle());
        ttf.setText("Secend Tab");
        fmList.add(ttf);

        ttf = FragmentFactory.newFragmentInstance(TestTabFragment.class, new Bundle());
        ttf.setText("Third Tab");
        fmList.add(ttf);

        SimpleTabAdapter adapter = new SimpleTabAdapter(getSupportFragmentManager(), fmList);
        ArrayList<TabLayout.Tab> list = new ArrayList<>(3);

        /*list.add(mTablayout.newTab().setText("First").setIcon(R.drawable.ic_launcher)
                .setCustomView(R.layout.layout_custom_tab_item));
        list.add(mTablayout.newTab().setText("Secend").setIcon(R.drawable.ic_launcher)
                .setCustomView(R.layout.layout_custom_tab_item));
        list.add(mTablayout.newTab().setText("Third").setIcon(R.drawable.ic_launcher)
                .setCustomView(R.layout.layout_custom_tab_item));*/

        list.add(mTablayout.newTab().setText("First").setIcon(R.drawable.ic_launcher));
        list.add(mTablayout.newTab().setText("Secend").setIcon(R.drawable.ic_launcher));
        list.add(mTablayout.newTab().setText("Third").setIcon(R.drawable.ic_launcher));

        setTabs(adapter, list);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Toast.makeText(this, tab.getText(), Toast.LENGTH_SHORT).show();
    }
}
