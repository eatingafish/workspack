package com.example.frag.dingdan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adapter.dingdan.PaymentAdapter;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.bean.dingdan.DetailList;
import com.example.bean.dingdan.OrderList;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.example.presenter.dingdan.AllOrderPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 支付页面
 */
public class Fragment_Payment extends Fragment {
    @BindView(R.id.paymentRecycleView)
    RecyclerView paymentRecycleView;
    Unbinder unbinder;
    private PaymentAdapter paymentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlist_fragment_payment, container, false);
        unbinder = ButterKnife.bind(this, view);

        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        paymentRecycleView.setLayoutManager(linearLayoutManager);

        //数据库
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();
        paymentAdapter = new PaymentAdapter(getContext());
        long userId = loginInfos.get(0).getUserId();
        String sessionId = loginInfos.get(0).getSessionId();
        AllOrderPresenter allOrderPresenter = new AllOrderPresenter(new getData());
        allOrderPresenter.reqeust(userId,sessionId,1,1,5);


        paymentRecycleView.setAdapter(paymentAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class getData implements DataCall<Result<List<OrderList<List<DetailList>>>>> {

        @Override
        public void success(Result<List<OrderList<List<DetailList>>>> data) {
            List<OrderList<List<DetailList>>> orderList = data.getOrderList();
            paymentAdapter.additem(orderList);
            paymentAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
