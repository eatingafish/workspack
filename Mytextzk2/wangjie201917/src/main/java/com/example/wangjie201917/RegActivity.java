package com.example.wangjie201917;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.Result;
import com.example.presenter.RegPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class RegActivity extends AppCompatActivity {

    @BindView(R.id.regphone)
    EditText regphone;
    @BindView(R.id.regyzm)
    EditText regyzm;
    @BindView(R.id.regpwd)
    EditText regpwd;
    @BindView(R.id.but_reg)
    Button butReg;
    @BindView(R.id.textdl)
    TextView textdl;
    private RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        regPresenter = new RegPresenter(new getData());
    }

    //点击注册
    @OnClick(R.id.but_reg)
    public void onViewClicked() {
        String phone = regphone.getText().toString();
        String pwd = regpwd.getText().toString();
        regPresenter.request(phone, pwd);
    }

    //点击返回登录
    @OnClick(R.id.textdl)
    public void onView() {
        startActivity(new Intent(RegActivity.this,MainActivity.class));
        finish();
    }

    private class getData implements Consumer<Result> {
        @Override
        public void accept(Result result) throws Exception {
            Toast.makeText(RegActivity.this, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        regPresenter.unBind();
    }
}
