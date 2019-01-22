package com.example.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.bean.Judge;
import com.example.bean.LoginReg.LoginInfo;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.JudgeDao;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.MainActivity;
import com.example.myfirstp.R;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyshezhiActivity extends AppCompatActivity {

    @BindView(R.id.but_exit)
    Button butExit;
    private JudgeDao loginInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshezhi);
        ButterKnife.bind(this);
        //获取数据库
        DaoSession daoSession = DaoMaster.newDevSession(this, JudgeDao.TABLENAME);
        loginInfoDao = daoSession.getJudgeDao();
        MobclickAgent.onProfileSignOff();
    }

    @OnClick(R.id.but_exit)
    public void onViewClicked() {
        List<Judge> loginInfos = loginInfoDao.loadAll();
        for (int i = 0; i < loginInfos.size(); i++) {
            Judge judge = loginInfos.get(i);
            loginInfoDao.delete(judge);
        }
        Intent intent = new Intent(MyshezhiActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
