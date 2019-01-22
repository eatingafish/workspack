package com.example.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.mypageAdapter.ShwoAddressAdapter;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.bean.mypage.Addaddressbean;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.example.presenter.mypage.ShowAddressPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展示收货地址列表
 */
public class MyAddressActivity extends AppCompatActivity {

    @BindView(R.id.mTv_MyAddress)
    TextView mTvMyAddress;
    @BindView(R.id.myaddress_finsh)
    Button myaddressFinsh;
    @BindView(R.id.myaddress_recycler)
    RecyclerView myaddressRecycler;
    @BindView(R.id.myaddress_but_add)
    Button myaddressButAdd;
    private ShwoAddressAdapter shwoAddressAdapter;
    private ShowAddressPresenter showAddressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);

        //设置默认布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        myaddressRecycler.setLayoutManager(linearLayoutManager);

        //数据库 查询userid  sessionid
        DaoSession daoSession = DaoMaster.newDevSession(MyAddressActivity.this, LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();
        String sessionId = loginInfos.get(0).getSessionId();
        int userId = (int) loginInfos.get(0).getUserId();
        showAddressPresenter = new ShowAddressPresenter(new getData());
        showAddressPresenter.reqeust(userId,sessionId);
        //适配器
        shwoAddressAdapter = new ShwoAddressAdapter(this);
        myaddressRecycler.setAdapter(shwoAddressAdapter);
    }

    @OnClick(R.id.myaddress_but_add)
    public void onViewClicked() {
        startActivity(new Intent(MyAddressActivity.this,AddAddress.class));
        finish();
    }

    private class getData implements DataCall<Result<List<Addaddressbean>>> {
        @Override
        public void success(Result<List<Addaddressbean>> data) {
            if (data.getStatus().equals("0000"))
            {
                Toast.makeText(MyAddressActivity.this, "成功", Toast.LENGTH_SHORT).show();
                List<Addaddressbean> result = data.getResult();
                Log.e("wj","收货地址ID"+data.getResult().get(0).getId());
                shwoAddressAdapter.additem(result);
            }
            else {
                Toast.makeText(MyAddressActivity.this, "失败"+data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(MyAddressActivity.this, ""+e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }
}
