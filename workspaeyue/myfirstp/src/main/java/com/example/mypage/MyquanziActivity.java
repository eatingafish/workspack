package com.example.mypage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.adapter.mypageAdapter.MyQuanziAdapter;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.bean.mypage.MyCirclebean;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.example.presenter.mypage.MyquanziPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyquanziActivity extends AppCompatActivity {

    @BindView(R.id.myquanrecycler)
    RecyclerView myquanrecycler;
    @BindView(R.id.image_del)
    ImageView imageDel;
    private long userId;
    private String sessionId;
    private MyQuanziAdapter myQuanziAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquanzi);
        ButterKnife.bind(this);
        //获取数据库
        DaoSession daoSession = DaoMaster.newDevSession(MyquanziActivity.this, LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        myquanrecycler.setLayoutManager(linearLayoutManager);

        userId = loginInfos.get(0).getUserId();
        sessionId = loginInfos.get(0).getSessionId();

        MyquanziPresenter myquanziPresenter = new MyquanziPresenter(new getData());
        myquanziPresenter.reqeust(userId,sessionId,1,5);

        myQuanziAdapter = new MyQuanziAdapter(this);
        myquanrecycler.setAdapter(myQuanziAdapter);

    }

    private class getData implements DataCall<Result<List<MyCirclebean>>> {

        @Override
        public void success(Result<List<MyCirclebean>> data) {
            List<MyCirclebean> result = data.getResult();
            if (data.getStatus().equals("0000"))
            {
                myQuanziAdapter.addItem(result);
                myQuanziAdapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(MyquanziActivity.this, "展示失败"+data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(MyquanziActivity.this, "展示失败"+e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }
}
