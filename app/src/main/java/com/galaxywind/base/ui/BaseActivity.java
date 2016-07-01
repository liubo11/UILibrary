package com.galaxywind.base.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.main.R;
import com.galaxywind.base.adapter.HeaderRecyclerAdapter;
import com.galaxywind.utils.ColorUtils;
import com.galaxywind.utils.ViewUtils;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-06-29.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.layout_base_activity);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        setContent_(LayoutInflater.from(this).inflate(layoutResID, viewGroup, false), null);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.layout_base_activity);
        setContent_(view, null);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(R.layout.layout_base_activity);
        setContent_(view, params);
    }

    private DrawerLayout mDrawer;

    private void setContent_(View view, ViewGroup.LayoutParams params) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        if (null != params) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(params);
            viewGroup.addView(view, lp);
        } else {
            viewGroup.addView(view);
        }
        mDrawer = (DrawerLayout) findViewById(R.id.base_drawer);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


        bindLeftClick();

        initMenuList();
    }

    private void bindLeftClick() {
        setCBLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opposeLeftDrawer();
            }
        });
    }

    private void opposeLeftDrawer() {
        if (mDrawer != null) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawers();
            } else {
                mDrawer.openDrawer(GravityCompat.START);
            }
        }
    }

    protected void setCBLeftClickListener(View.OnClickListener listener) {
        findViewById(R.id.cb_back).setOnClickListener(listener);
    }

    protected void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }


    /*
     * after setContentView
     */
    private void initMenuList() {
        RecyclerView rView = (RecyclerView) findViewById(R.id.base_menu_list);

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(30);
        LinearLayoutManager llManager = new LinearLayoutManager(this);



        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        llManager.setSmoothScrollbarEnabled(true);
        adapter.setHeaderView(R.layout.test_menu_header);
        rView.setLayoutManager(llManager);
        rView.setHasFixedSize(true);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        rView.setAdapter(adapter);
    }

    private class MyDecoration extends RecyclerView.ItemDecoration {
        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
        private int mOrientation;
        private int mDividerSize = 4;
        private Paint mPaint;

        public MyDecoration(Context context, int orientation) {
            setOrientation(orientation);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(0x80FFFFFF & ColorUtils.getRandomColor());
            mPaint.setStyle(Paint.Style.FILL);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            System.out.println("ondraw " + state);
            //c.drawColor(getRandomColor());
            //c.drawColor(Color.GRAY);
            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, 4);
            System.out.println("getItemOffsets outRect=" + outRect + ",state=" + state);
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft() + 20;
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDividerSize;

                c.drawRect(left, top, right, bottom, mPaint);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDividerSize;

                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    private class MyRecyclerAdapter extends HeaderRecyclerAdapter<Integer> {

        private ArrayList<Integer> datas;
        private int size;

        public MyRecyclerAdapter(int num) {
            datas = new ArrayList<>(num);
            size = num;
            for (int i = 0; i < num; i++) {
                datas.add(i);
            }
            addDatas(datas);
        }

        @Override
        public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.test_recycler_items, parent, false);
            return new MyRecyclerHolder(v);
        }

        @Override
        public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, Integer data) {
            if(viewHolder instanceof MyRecyclerHolder) {
                ((MyRecyclerHolder) viewHolder).setPosition(realPosition);
            }
        }
    }

    private class MyRecyclerHolder extends HeaderRecyclerAdapter.Holder implements View.OnClickListener {
        private TextView tView;
        private int mPosition;

        @SuppressWarnings("deprecation")
        public MyRecyclerHolder(View itemView) {
            super(itemView);
            itemView.setBackgroundDrawable(buildItemDrawable());
            itemView.setOnClickListener(this);

            tView = (TextView) itemView.findViewById(R.id.text);
        }

        public void setPosition(int p) {
            this.mPosition = p;
            tView.setText(" position=" + p);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(tView.getContext(), "click p=" + mPosition, Toast.LENGTH_SHORT).show();
        }

        private Drawable buildItemDrawable() {
            return ViewUtils.buildColorBackgroundDrawable(
                    0, 0, 0x80ff0000, 0xFFFFFFFF);
        }
    }
}
