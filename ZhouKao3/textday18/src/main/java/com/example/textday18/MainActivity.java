package com.example.textday18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.umeng.analytics.MobclickAgent;

public class MainActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.edit_phone)
    EditText editPhone;
    @butterknife.BindView(R.id.edit_pwd)
    EditText editPwd;
    @butterknife.BindView(R.id.button_login)
    Button buttonLogin;
    @butterknife.BindView(R.id.button_reg)
    Button buttonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);


    }

    @butterknife.OnClick({R.id.button_login, R.id.button_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                break;
            case R.id.button_reg:
                startActivity(new Intent(this,RegActivity.class));
                break;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
