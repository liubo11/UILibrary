package com.demo.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.galaxywind.base.adapter.BaseTabAdapter;
import com.galaxywind.base.ui.BaseTabActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-07-01.
 */
public class TabActivity extends BaseTabActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        TabLayout tl = (TabLayout) findViewById(R.id.base_tab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.base_tab_container);

        ArrayList<TestTabFragment> fmList = new ArrayList<>();

        TestTabFragment ttf = new TestTabFragment();
        ttf.setText("First Tab");
        fmList.add(ttf);

        ttf = new TestTabFragment();
        ttf.setText("Secend Tab");
        fmList.add(ttf);

        ttf = new TestTabFragment();
        ttf.setText("Third Tab");
        fmList.add(ttf);

        BaseTabAdapter bta = new BaseTabAdapter(getSupportFragmentManager(), fmList);
        viewPager.setAdapter(bta);

        tl.setupWithViewPager(viewPager);
        tl.removeAllTabs();
        tl.setSelectedTabIndicatorHeight(0);

        tl.addTab(tl.newTab().setText("First").setIcon(R.drawable.ic_launcher)
                .setCustomView(R.layout.layout_custom_tab_item), 0);
        tl.addTab(tl.newTab().setText("Secend").setIcon(R.drawable.ic_launcher)
                .setCustomView(R.layout.layout_custom_tab_item), 1);
        tl.addTab(tl.newTab().setText("Third").setIcon(R.drawable.ic_launcher)
                .setCustomView(R.layout.layout_custom_tab_item), 2);

        //tl.setTabTextColors(Color.GRAY, Color.BLACK);
    }
}
