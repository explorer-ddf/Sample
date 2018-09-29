package com.didichuxing.restartapp.demo;

import android.app.Application;
import android.util.Log;

import java.util.List;

/**
 * Created by ddf on 2018/9/27
 */
public class MyApplication extends Application {

    public static List<String> mDatas;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication", "onCreate");
    }
}
