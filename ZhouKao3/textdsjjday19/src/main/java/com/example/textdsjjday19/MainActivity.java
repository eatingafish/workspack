package com.example.textdsjjday19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button1:
                Toast.makeText(this, "13 14 15 17 20 23 28 42", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(this, "13 14 15 17 20 23 28 42", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(this, "13 14 15 17 20 23 28 42", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
