package com.demo.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.demo.main.R;
import com.galaxywind.base.ui.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by Administrator on 2016-07-14.
 */
public class BaseTestPage extends BaseActivity {

    private static final String TAG = "TestLaunch ";

    public static void startTestActivity(int lanuchMode, Context context, Class clazz) {

        Intent intent = new Intent(context, clazz);
        SimpleFormatter sf = new SimpleFormatter();
        DateFormat simpleDateFormat = SimpleDateFormat.getDateInstance();

        String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        intent.putExtra("name", clazz.getSimpleName());
        intent.putExtra("time", time);
        intent.putExtra("mode", lanuchMode);

        if (lanuchMode != 0) {
            intent.setFlags(lanuchMode);
        }
        context.startActivity(intent);
    }

    @Override
    protected void initField() {

    }

    @Override
    protected void setContent() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }

    private String started;

    private String name;
    private String time;
    private int mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBackStack.getInstance().push(this);


        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        time = intent.getStringExtra("time");
        mode = intent.getIntExtra("mode", 0);

        if (name == null) {
            name = getClass().getSimpleName();
        }

        if (started == null) {
            System.out.println(TAG+"this activity is start on first time");
            if (time != null) {
                started = time;
            }
        } else {
            System.out.println(TAG+"last created time="+started);
        }

        if (time != null) {
            System.out.println(TAG+"time="+time+",name="+name+", mode="+Integer.toHexString(mode));
        } else {
            System.out.println(TAG+"onCreate Base Test Page");
        }


        setContentView(R.layout.test_launch_mode);

        findViewById(R.id.start_base).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity(Intent.FLAG_ACTIVITY_SINGLE_TOP, BaseTestPage.this, BaseTestPage.class);
            }
        });
        findViewById(R.id.start_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity(0, BaseTestPage.this, PageA.class);
            }
        });
        findViewById(R.id.start_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity(Intent.FLAG_ACTIVITY_CLEAR_TOP, BaseTestPage.this, PageB.class);
            }
        });
        findViewById(R.id.start_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK, BaseTestPage.this, PageB.class);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println(TAG+ name +"->onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(TAG+ name +"->onDestroy");
        System.out.println(TAG+" "+ ActivityBackStack.getInstance().dump());
        //ActivityBackStack.getInstance().remove(this.getClass());
    }

    public static int getLanchMode() {
        return 0;
    }
}
