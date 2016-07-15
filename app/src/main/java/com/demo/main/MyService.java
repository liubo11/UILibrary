package com.demo.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.demo.data.ActionMessage;
import com.demo.test.PageA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016-07-15.
 */
public class MyService extends Service {
    private static final String TAG = "MyService ";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private EventBus eventBus;
    @Override
    public void onCreate() {
        super.onCreate();
        eventBus  = EventBus.getDefault();
        eventBus.register(this);
        printLog("onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        printLog("onDestroy");
        eventBus.unregister(this);
        eventBus = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(ActionMessage msg) {

        if (msg.getAction() == ActionMessage.START_ACTIVITY_MSG) {

            startActivity(new Intent(this, PageA.class));

        }

    }

    private void printLog(String log) {
        System.out.println(TAG + log);
    }
}
