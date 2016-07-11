package com.demo.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.galaxywind.base.ui.BaseFragment;

/**
 * Created by Administrator on 2016-07-01.
 */
public class TestFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initField() {

    }

    @Override
    protected void setContent() {
        setContentView(R.layout.test_fragment_layout);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
