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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.test_fragment_layout, container, false);

        //TextView tv = vg.findViewById(R.id.)

        return  vg;
    }
}
