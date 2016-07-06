package com.galaxywind.base.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galaxywind.base.R;
import com.galaxywind.base.fragment.impl.ICtrlBar;
import com.galaxywind.base.ui.BaseFragment;
import com.galaxywind.utils.Logger;

/**
 * Created by Administrator on 2016-07-05.
 */
public class ControlBarFragment extends BaseFragment implements ICtrlBar {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d("Control bar fragment create view");
        return inflater.inflate(R.layout.layout_control_bar, container, false);
    }

    @Override
    public void addLeftButton(@DrawableRes int drawable, View.OnClickListener listener) {

    }

    @Override
    public void addLeftTextButton(@StringRes int text, View.OnClickListener listener) {

    }

    @Override
    public void addBackButton() {
        //TODO
        clearLeftAdded();
        addLeftButton(R.drawable.img_cb_back, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getActivity() != null) {
                            getActivity().onBackPressed();
                        }
                    }
                }
        );
    }

    @Override
    public void addRightButton(@DrawableRes int drawable, View.OnClickListener listener) {

    }

    @Override
    public void addRightTextButton(@StringRes int text, View.OnClickListener listener) {

    }

    @Override
    public void clearLeftAdded() {

    }

    @Override
    public void clearRightAdded() {

    }

    @Override
    public void setTitle(CharSequence title) {

    }

    @Override
    public void setTitleColor(@ColorInt int color) {

    }

    @Override
    public void setTitleSize(int size) {

    }

    @Override
    public void setBarBackgroud(Drawable drawable) {

    }

    @Override
    public void setImmersedStyle(boolean immersedStyle) {

    }
}
