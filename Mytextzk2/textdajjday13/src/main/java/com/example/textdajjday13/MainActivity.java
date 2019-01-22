package com.example.textdajjday13;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(this, "SD卡可用", Toast.LENGTH_SHORT).show();
            String path = Environment.getExternalStorageDirectory().getPath();
            Log.e("wj","SD卡路径"+path);
        }else {
            Toast.makeText(this, "当前SD卡不可用", Toast.LENGTH_SHORT).show();
        }
    }
}
