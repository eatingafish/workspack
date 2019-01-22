package com.example.myfirstp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.LoginReg.RegInfo;
import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.presenter.RegPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegPage extends AppCompatActivity {

    @BindView(R.id.tv_regphone)
    EditText tvRegphone;
    @BindView(R.id.tv_yanzheng)
    EditText tvYanzheng;
    @BindView(R.id.tv_getyanzheng)
    TextView tvGetyanzheng;
    @BindView(R.id.iv_reglock)
    ImageView ivReglock;
    @BindView(R.id.tv_regpassward)
    EditText tvRegpassward;
    @BindView(R.id.tv_reglogin)
    TextView tvReglogin;
    @BindView(R.id.but_reg)
    Button butReg;
    private RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);
        ButterKnife.bind(this);
        regPresenter = new RegPresenter(new Successreg());

    }

    @OnClick(R.id.but_reg)
    public void onViewClicked() {
        String tvregp = tvRegphone.getText().toString();
        String tvregpwd = tvRegpassward.getText().toString();
        regPresenter.reqeust(tvregp,tvregpwd);
    }

    @OnClick(R.id.tv_reglogin)
    public void regLogin()
    {
        startActivity(new Intent(this,MainActivity.class));
    }


    private class Successreg implements DataCall<Result> {
        @Override
        public void success(Result data) {
            Toast.makeText(RegPage.this, "注册成功"+data.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(RegPage.this, "注册失败"+e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }
}
