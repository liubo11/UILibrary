package com.galaxywind.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016-06-29.
 */
public class BaseFragment extends Fragment {



    public static Fragment newInstance(Class<?> clazz, Bundle extra) {
        try {
            BaseFragment fragment = (BaseFragment) clazz.newInstance();
            fragment.setArguments(extra);
            return  fragment;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extra = getArguments();

    }
}
