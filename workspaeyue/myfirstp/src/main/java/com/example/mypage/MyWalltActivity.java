package com.example.mypage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.mypageAdapter.MyWalletAdapter;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.bean.mypage.WalletBean;
import com.example.bean.mypage.WalletJia;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.example.presenter.mypage.MyWalletPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWalltActivity extends AppCompatActivity {

    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.myWallet_list)
    ListView myWalletList;
    private MyWalletPresenter myWalletPresenter;
    int page = 1;
    int count = 1;
    private MyWalletAdapter myWalletAdapter;
    ArrayList<WalletJia> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallt);
        ButterKnife.bind(this);
        //获取数据库 查询数据库
        DaoSession daoSession =DaoMaster.newDevSession(this,LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();
        //获取到sessionid userid
        String sessionId = loginInfos.get(0).getSessionId();
        int userId = (int) loginInfos.get(0).getUserId();
        myWalletPresenter = new MyWalletPresenter(new getData());
        myWalletPresenter.reqeust(userId,sessionId,page,count);

        //造假数据
        myWalletAdapter = new MyWalletAdapter(this, list);
        for (int i = 0; i < 10; i++) {
            WalletJia walletJia = new WalletJia("￥1200.00","2019-1-7");
            list.add(walletJia);
        }
        myWalletList.setAdapter(myWalletAdapter);
    }

    private class getData implements DataCall<Result<WalletBean>> {
        @Override
        public void success(Result<WalletBean> data) {
            Toast.makeText(MyWalltActivity.this, "余额"+data.getResult().getBalance(), Toast.LENGTH_SHORT).show();
            balance.setText(data.getResult().getBalance()+"");
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(MyWalltActivity.this, ""+e.getCode(), Toast.LENGTH_SHORT).show();
        }   
    }
}
