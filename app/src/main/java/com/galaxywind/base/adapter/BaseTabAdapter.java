package com.galaxywind.base.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-07-01.
 */
public class BaseTabAdapter extends FragmentStatePagerAdapter{
    private List<? extends Fragment> mPagers;

    public BaseTabAdapter(@NonNull FragmentManager fm, @NonNull List<? extends Fragment> pagers) {
        super(fm);
        this.mPagers = pagers;
    }


    @Override
    public Fragment getItem(int position) {
        return mPagers.get(position);
    }

    @Override
    public int getCount() {
        return mPagers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page "+position;
    }


}
