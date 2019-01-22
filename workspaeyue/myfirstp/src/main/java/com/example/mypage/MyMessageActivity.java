package com.example.mypage;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.LoginReg.LoginInfo;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMessageActivity extends AppCompatActivity {

    @BindView(R.id.mmImageview)
    SimpleDraweeView mmImageview;
    @BindView(R.id.mmName)
    TextView mmName;
    @BindView(R.id.mmpwd)
    TextView mmpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        ButterKnife.bind(this);
        DaoSession daoSession = DaoMaster.newDevSession(MyMessageActivity.this, LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();
        mmImageview.setImageURI(Uri.parse(loginInfos.get(0).getHeadPic()));
        mmName.setText(loginInfos.get(0).getNickName());
        mmpwd.setText(loginInfos.get(0).getSessionId());
        Toast.makeText(this, ""+loginInfos.get(0).getUserId(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+loginInfos.get(0).getSessionId(), Toast.LENGTH_SHORT).show();

    }
}
