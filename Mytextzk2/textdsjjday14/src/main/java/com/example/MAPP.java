package com.example;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
