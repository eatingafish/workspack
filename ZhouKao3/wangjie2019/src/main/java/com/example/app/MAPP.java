package com.example.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco
        Fresco.initialize(this);
    }
}
