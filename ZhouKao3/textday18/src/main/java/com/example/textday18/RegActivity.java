package com.example.textday18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity {

    @BindView(R.id.reg_phone)
    EditText regPhone;
    @BindView(R.id.reg_pwd)
    EditText regPwd;
    @BindView(R.id.but_reg)
    Button butReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.but_reg)
    public void onViewClicked() {
        startActivity(new Intent(this,MainActivity.class));
    }
}
