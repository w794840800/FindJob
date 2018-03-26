package com.example.wanglei.findjob.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by wanglei on 18-3-24.
 */

public class BaseApplication extends Application{
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
    mContext = this;
    }
    public static Context getBaseCOntext(){

        return mContext;
    }
}
