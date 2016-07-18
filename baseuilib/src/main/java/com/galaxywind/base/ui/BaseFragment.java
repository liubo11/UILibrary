/*
*Copyright@ GALAXYWIND Network Systems Co.Ltd.
*Function: BaseFragment
*Creator: LiuBo
*Create time: 2016-06-29
*/
package com.galaxywind.base.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @since 2016-06-29
 */
public abstract class BaseFragment extends Fragment {
    protected ViewGroup mContainer;
    protected View mRootView;

    private int mRootLayout;
    protected Bundle mExtra;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExtra = getArguments();

        initField();
        setContent();
    }

    /**
     * 初始化类属性
     */
    protected abstract void initField();

    /**
     * 设置布局
     * <p>{@link #setContentView(int)}
     * <p>{@link #setContentView(View)}
    */
    protected abstract void setContent();
    /**
     * 初始化控件 监听事件
     */
    protected abstract void initView();

    /**
     * 初始化数据，比如adapter
     */
    protected abstract void initDatas();

    protected void setContentView(@LayoutRes int layout) {
        this.mRootLayout = layout;
    }
    protected void setContentView(@NonNull View view) {
        this.mRootView = view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContainer = container;
        if (mRootView != null) {
            return mRootView;
        } else if (mRootLayout != 0) {
            mRootView = inflater.inflate(mRootLayout, container, false);
            return mRootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mRootView == null) {
            mRootView = getView();
        }
        initView();
        initDatas();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;
        mContainer = null;
    }

    public boolean isCreatedView() {
        return mRootView != null;
    }

    public BaseFragment() {
        //do not call directly
    }

    public <T> T findViewById(@IdRes int id) {
        if (isCreatedView()) {
            return (T) mRootView.findViewById(id);
        }
        return null;
    }
}
