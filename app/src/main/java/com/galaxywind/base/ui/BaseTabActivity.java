package com.galaxywind.base.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.demo.main.R;

/**
 * Created by Administrator on 2016-06-29.
 */
public class BaseTabActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContentView() {
        super.setContentView(R.layout.layout_top_tab_page);
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
}
