package com.demo.test;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by Administrator on 2016-07-14.
 */
public final class ActivityBackStack {

    private Stack<WeakReference<BaseTestPage>> stack;

    private static ActivityBackStack instance;


    private ActivityBackStack() {
        stack = new Stack<>();
    }
    public static final ActivityBackStack getInstance() {
        if (instance == null) {
            instance = new ActivityBackStack();
        }
        return instance;
    }

    public void push(BaseTestPage page) {
        WeakReference<BaseTestPage> wp = new WeakReference<BaseTestPage>(page);
        stack.push(wp);
    }

    public BaseTestPage remove(Class clazz) {

        //TODO
        return null;
    }

    public BaseTestPage pop() {
        WeakReference<BaseTestPage> wp = stack.pop();
        return wp.get();
    }

    public String dump() {
        StringBuffer sb = new StringBuffer();
        sb.append("StackDump size=").append(stack.size()).append("\n");

        for (int i = 0; i < stack.size(); i++) {
            BaseTestPage baseTestPage = stack.get(i).get();
            sb.append("index ").append(i).append(" ");
            if (baseTestPage == null) {
                sb.append("is null");
            } else {
                sb.append("is ").append(baseTestPage.getClass().getSimpleName());
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
