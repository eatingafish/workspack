package com.example.app;

import android.app.Application;

import com.example.fresco_demo1.ImageLoaderConfig;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}