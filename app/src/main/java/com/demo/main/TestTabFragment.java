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
public class TestTabFragment extends BaseFragment {
    private TextView mTextView;
    private String mMsg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate isCreatedView="+isCreatedView());
    }

    @Override
    protected void initField() {

    }

    @Override
    protected void setContent() {
        setContentView(R.layout.test_tab_fragment_layout);
    }

    @Override
    protected void initView() {
        mTextView = (TextView) mRootView.findViewById(R.id.text);
        if (null != mMsg) {
            mTextView.setText(mMsg);
        }
    }

    @Override
    protected void initDatas() {

    }

    public void setText(@Nullable String msg) {
        this.mMsg = msg;
        if (null != mTextView) {
            mTextView.setText(msg);
        }
    }

}
