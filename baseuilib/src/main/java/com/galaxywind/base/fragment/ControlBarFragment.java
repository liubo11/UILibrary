package com.galaxywind.base.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.galaxywind.base.R;
import com.galaxywind.base.fragment.impl.ICtrlBar;
import com.galaxywind.base.ui.BaseFragment;
import com.galaxywind.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-07-05.
 */
public class ControlBarFragment extends BaseFragment implements ICtrlBar {
    private static final int MAX_ADD_BOTTON_NUM = 3;
    private int mMarkColor;
    private int mMarkSize;
    private int mItemSize;
    private int mTitleDefPadding;

    private List<ABarItem> mLeftBtnList = new ArrayList<>(MAX_ADD_BOTTON_NUM);
    private List<ABarItem> mRightBtnList = new ArrayList<>(MAX_ADD_BOTTON_NUM);

    private CharSequence mTitleChar;
    private Integer mTitleColor;
    private Drawable mTitleBarDrawable;

    private ViewGroup mLeftContainer;
    private ViewGroup mRightContainer;
    private TextView mTitleView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("Control bar fragment created");
    }

    @Override
    protected void initField() {
        mMarkColor = getResources().getColor(R.color.control_bar_mark);
        mMarkSize = getResources().getDimensionPixelSize(R.dimen.control_bar_mark_size);
        mItemSize = getResources().getDimensionPixelSize(R.dimen.control_bar_size);
        mTitleDefPadding = getResources().getDimensionPixelSize(R.dimen.control_bar_text_padding);
    }

    @Override
    protected void setContent() {
        setContentView(R.layout.layout_control_bar);
    }

    @Override
    protected void initView() {
        mLeftContainer = findViewById(R.id.cb_left_container);
        mRightContainer = findViewById(R.id.cb_right_container);
        mTitleView = findViewById(R.id.cb_title);

        refreshCtrlBar();
        notifyChanged();
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void addLeftButton(@DrawableRes int drawable, View.OnClickListener listener) {
        mLeftBtnList.add(new ImageItem(drawable, listener));
    }

    @Override
    public void addLeftTextButton(@StringRes int text, View.OnClickListener listener) {
        mLeftBtnList.add(new TextItem(text, listener));
    }

    @Override
    public void addRightButton(@DrawableRes int drawable, View.OnClickListener listener) {
        mRightBtnList.add(new ImageItem(drawable, listener));
    }

    @Override
    public void addRightTextButton(@StringRes int text, View.OnClickListener listener) {
        mRightBtnList.add(new TextItem(text, listener));
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
        notifyChanged();
    }

    @Override
    public void notifyChanged() {
        if (isInflated()) {
            int maxSize = Math.max(mLeftBtnList.size(), mRightBtnList.size());
            int leftPadding = maxSize * mItemSize + mTitleDefPadding;
            int rightPadding = leftPadding;
            int topPadding = mTitleView.getPaddingTop();
            int bottomPadding = mTitleView.getPaddingBottom();
            mTitleView.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

            mLeftContainer.removeAllViews();
            mRightContainer.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            for (ABarItem item : mLeftBtnList) {
                item.inflateView(inflater, mLeftContainer, true);
            }
            for (ABarItem item : mRightBtnList) {
                item.inflateView(inflater, mRightContainer, true);
            }
        }
    }

    @Override
    public void clearLeftAdded() {
        mLeftBtnList.clear();
    }

    @Override
    public void clearRightAdded() {
        mRightBtnList.clear();
    }

    @Override
    public void setTitle(CharSequence title) {
        this.mTitleChar = title;
        refreshCtrlBar();
    }

    @Override
    public void setTitleColor(@ColorInt int color) {
        this.mTitleColor = color;
        refreshCtrlBar();
    }

    @Override
    public void setBarBackgroud(Drawable drawable) {
        this.mTitleBarDrawable = drawable;
        refreshCtrlBar();
    }

    private void refreshCtrlBar() {
        if (isInflated()) {
            mTitleView.setText(mTitleChar == null ? "" : mTitleChar);
            if (mTitleColor != null) {
                mTitleView.setTextColor(mTitleColor);
            }
            if (mTitleBarDrawable != null) {
                mRootView.setBackgroundDrawable(mTitleBarDrawable);
            }
        }
    }

    /**
     * 布局中的view都已初始化完成
     */
    private boolean isInflated() {
        return null != mTitleView;
    }

    private final class TextItem extends ABarItem {
        public TextItem(int res, View.OnClickListener listener) {
            super(res, listener);
        }
        @Override
        public View inflateView(LayoutInflater inflater, ViewGroup parent, boolean attach) {
            TextView textView = (TextView) inflater.inflate(
                    R.layout.layout_control_bar_text_item, parent, false);
            textView.setText(mResId);
            mItemView = textView;
            mItemView.setOnClickListener(mListener);

            if (attach) { parent.addView(mItemView); }

            return textView;
        }
    }

    private final class ImageItem extends ABarItem {
        public ImageItem(int res, View.OnClickListener listener) {
            super(res, listener);
        }
        @Override
        public View inflateView(LayoutInflater inflater, ViewGroup parent, boolean attach) {
            ImageView imageView = (ImageView) inflater.inflate(
                    R.layout.layout_control_bar_image_item, parent, false);
            imageView.setImageResource(mResId);
            mItemView = imageView;
            mItemView.setOnClickListener(mListener);

            if (attach) { parent.addView(mItemView); }

            return mItemView;
        }
    }

    private abstract class ABarItem {
        protected View.OnClickListener mListener;
        protected int mResId;
        protected View mItemView;

        public ABarItem(int res, View.OnClickListener listener) {
            this.mResId = res;
            this.mListener = listener;
        }

        void setOnClickListener(View.OnClickListener listener) {
            mListener = listener;
        }

        void showMark(int color) {
            //TODO
        }
        void setResId(int res) {
            mResId = res;
        }
        abstract View inflateView(LayoutInflater inflater, ViewGroup parent, boolean attach);
    }
}