package com.example.zhoukao3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.axlecho.sakura.PlayerView;

public class MainActivity extends AppCompatActivity {

    private PlayerView play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play);
        play.setVideoUrl("http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4");
    }
}
