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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.test_tab_fragment_layout, container, false);
        mTextView = (TextView) vg.findViewById(R.id.text);
        if (null != mMsg) {
            mTextView.setText(mMsg);
        }
        return  vg;
    }

    public void setText(@Nullable String msg) {
        this.mMsg = msg;
        if (null != mTextView) {
            mTextView.setText(msg);
        }
    }

}
