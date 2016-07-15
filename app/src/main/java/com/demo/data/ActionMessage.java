package com.demo.data;

/**
 * Created by Administrator on 2016-07-15.
 */
public class ActionMessage {

    public static final int START_ACTIVITY_MSG = 1;

    private int mAction;

    public ActionMessage(int action) {
        this.mAction = action;
    }

    public void setAction(int action) {

    }

    public int getAction() {
        return mAction;
    }


}
