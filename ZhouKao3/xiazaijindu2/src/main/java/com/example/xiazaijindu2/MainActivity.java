package com.example.xiazaijindu2;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.net.URL;

import me.shenfan.updateapp.UpdateService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //关键一句话
        UpdateService.Builder.create("http://111.202.98.39/soft.imtt.qq.com/browser/channel/liberty_spread/210/2139/qqbrowser_9.0.1.4775_20820.apk").build(this);


//或者设定参数
        UpdateService.Builder.create("http://111.202.98.39/soft.imtt.qq.com/browser/channel/liberty_spread/210/2139/qqbrowser_9.0.1.4775_20820.apk")

                .setStoreDir("update")
                .setIsSendBroadcast(true)
                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_SOUND)
                .setDownloadErrorNotificationFlag(Notification.DEFAULT_SOUND)
                .setIcoResId(android.R.drawable.ic_notification_clear_all)
                .setIcoSmallResId(android.R.drawable.ic_notification_overlay)
                .build(this);


    }
}
