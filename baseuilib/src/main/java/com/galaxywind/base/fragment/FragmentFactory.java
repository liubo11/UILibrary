package com.galaxywind.base.fragment;

import android.os.Bundle;

import com.galaxywind.base.ui.BaseActivity;
import com.galaxywind.base.ui.BaseFragment;

/**
 * Created by Administrator on 2016-07-06.
 */
public class FragmentFactory {

    public static final String EXTRA_KEY_USER_HANDLE = "com.base.basefragment.userhandle";
    public static final String EXTRA_KEY_COMM_ID = "com.base.basefragment.commid";
    public static final String EXTRA_KEY_HANDLE = "com.base.basefragment.handle";

    /**
     * @param userHandle 联动用户handle 0=invalid
     * @param commId 圈子id 0=invalid
     * @param handle 设备handle 0=invalid
     * @return extra
     */
    public static Bundle newBaseExtra(int userHandle, int commId, int handle) {
        Bundle extra = new Bundle();
        extra.putInt(EXTRA_KEY_USER_HANDLE, userHandle);
        extra.putInt(EXTRA_KEY_COMM_ID, commId);
        extra.putInt(EXTRA_KEY_HANDLE, handle);
        return extra;
    }

    public static ControlBarFragment newControlBar() {
        return newFragmentInstance(ControlBarFragment.class, new Bundle());
    }

    public static LeftMenuFragment newMoreMenu(BaseActivity.MenuStyle menuStyle) {
        return newFragmentInstance(LeftMenuFragment.class, new Bundle());
    }



    public static <T extends BaseFragment>T newFragmentInstance(Class<T> clazz, Bundle extra) {
        try {
            T bf = (T) clazz.newInstance();
            bf.setArguments(extra);
            return  bf;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("constructor is not visible");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("con not create this instance,"+clazz.getName());
        }
    }
}
