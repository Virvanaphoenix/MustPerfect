package com.feng.mustwin.application;

import android.app.Application;

/**
 * Created by wuwf on 2015/1/12.
 */
public class MyApplication extends Application {

    private static MyApplication context;

    public static MyApplication getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        // AVOSCloud.initialize(this, "tCuTnivPYRbQr02F65LKn1oD-gzGzoHsz", "NQ44HEXXSmFLb7Pf6AXN8MEO");
        // 使用推送服务时的初始化操作
        // MyInstallation.getCurrentInstallation(this).save();
    }

}
