package com.geektech.geektech.presenter;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


    }

    public static App getInstance() {
        return instance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
