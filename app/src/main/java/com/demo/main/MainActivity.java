package com.demo.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.galaxywind.base.ui.BaseActivity;

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

    }
}
