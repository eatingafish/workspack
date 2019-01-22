package com.example.myfirstp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.Judge;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.JudgeDao;
import com.example.dao.LoginInfoDao;
import com.example.presenter.RequestPresenter;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_passward)
    EditText tvPassward;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.check_remanber)
    CheckBox checkRemanber;
    @BindView(R.id.tv_remanber)
    TextView tvRemanber;
    @BindView(R.id.but_login)
    Button butLogin;
    @BindView(R.id.tv_kszhuce)
    TextView tvkszc;
    private RequestPresenter requestPresenter;
    Boolean canSee = true;
    private JudgeDao judgeDao;
    private LoginInfoDao loginInfoDao;
    private List<LoginInfo> loginInfos;
    private List<Judge> judges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requestPresenter = new RequestPresenter(new RegisterCall());

        //登陆后将数据添加到数据库中
        DaoSession daoSession2 = DaoMaster.newDevSession(MainActivity.this, LoginInfoDao.TABLENAME);
        loginInfoDao = daoSession2.getLoginInfoDao();
        //loginInfos = loginInfoDao.loadAll();
        //自动登录 勾选后查询数据库 有的话记住密码
        DaoSession daoSession = DaoMaster.newDevSession(this, JudgeDao.TABLENAME);
        judgeDao = daoSession.getJudgeDao();
        judges = judgeDao.loadAll();
        for (int i = 0; i < judges.size(); i++) {
            if (judges.get(i).getIschecked()) {

                tvPhone.setText(judges.get(0).getPhone());
                tvPassward.setText(judges.get(0).getPassward());
                checkRemanber.setChecked(judges.get(0).getIschecked());
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        }


    }


    //点击登录
    @OnClick(R.id.but_login)
    public void onViewClicked() {
        String tvphone = tvPhone.getText().toString();
        String tvpassword = tvPassward.getText().toString();
        requestPresenter.reqeust(tvphone, tvpassword);
    }

    //跳转到注册页面
    @OnClick(R.id.tv_kszhuce)
    public void startreg() {
        startActivity(new Intent(this, RegPage.class));
    }

    //点击隐藏密码
    @OnClick(R.id.iv_eye)
    public void hidepswd() {
        if (canSee == false) {
            //如果是不能看到密码的情况下
            tvPassward.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            canSee = true;
        } else {
            //如果是能看到密码的状态下
            tvPassward.setTransformationMethod(PasswordTransformationMethod.getInstance());
            canSee = false;
        }
    }

    /*
    登录
     */
    class RegisterCall implements DataCall<Result<LoginInfo>> {

        @Override
        public void success(Result<LoginInfo> data) {
            if (data.getStatus().equals("0000")) {
                String tvphone = tvPhone.getText().toString().trim();
                String tvpassword = tvPassward.getText().toString().trim();
                //自动登录
                Judge judge = new Judge(0, tvphone, tvpassword, checkRemanber.isChecked());
                judgeDao.insertOrReplace(judge);

                // 将登录的数据添加到数据库
                LoginInfo result = data.getResult();
                loginInfoDao.insertOrReplace(result);
                Toast.makeText(MainActivity.this, "登录成功" + data.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(MainActivity.this, "登录失败" + data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(MainActivity.this, "失败登录" + e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}


