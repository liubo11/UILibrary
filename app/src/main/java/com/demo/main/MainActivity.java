package com.demo.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxywind.base.adapter.HeaderRecyclerAdapter;
import com.galaxywind.base.ui.BaseActivity;
import com.galaxywind.utils.ViewUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-06-29.
 */
public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_main_activity);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TabActivity.class);
            }
        });

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(30);

        adapter.setHeaderView(R.layout.test_menu_header);
        initMenuList(adapter);
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
