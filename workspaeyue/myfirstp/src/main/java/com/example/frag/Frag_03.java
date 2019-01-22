package com.example.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.shopcart.ShopCartAdapter;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.bean.shopcart.ShopCartgoods;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.example.presenter.shopcart.ShopCartGoodsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 购物车页面
 */
public class Frag_03 extends Fragment {
    @BindView(R.id.recycler_shopcart)
    RecyclerView recyclerShopcart;
    @BindView(R.id.check_allCart)
    CheckBox checkAllCart;
    @BindView(R.id.quanxuan)
    TextView quanxuan;
    @BindView(R.id.heji)
    TextView heji;
    @BindView(R.id.text_total)
    TextView textTotal;
    @BindView(R.id.button_colose)
    Button buttonColose;
    @BindView(R.id.nR)
    RelativeLayout nR;
    Unbinder unbinder;
    private ShopCartGoodsPresenter shopCartGoodsPresenter;
    private ShopCartAdapter shopCartAdapter;
    private LinearLayoutManager linearLayoutManager;
    private long userId;
    private String sessionId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_03, container, false);
        unbinder = ButterKnife.bind(this, view);

        //获取数据库
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();

        userId = loginInfos.get(0).getUserId();
        sessionId = loginInfos.get(0).getSessionId();

        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerShopcart.setLayoutManager(linearLayoutManager);

        shopCartAdapter = new ShopCartAdapter(getContext());
        recyclerShopcart.setAdapter(shopCartAdapter);

        shopCartGoodsPresenter = new ShopCartGoodsPresenter(new getData());
        shopCartGoodsPresenter.reqeust(userId, sessionId);

        checkAllCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //调用adapter里面的全选/全不选方法
                shopCartAdapter.checkAll(isChecked);
                shopCartAdapter.setTotalPriceListener(new ShopCartAdapter.TotalPriceListener() {
                    @Override
                    public void totalPrice(double totalPrice) {
                        textTotal.setText("￥"+totalPrice);
                    }
                });
            }
        });
        return view;
    }



    private class getData implements DataCall<Result<List<ShopCartgoods>>> {
        @Override
        public void success(Result<List<ShopCartgoods>> data) {
            if (data.getStatus().equals("0000"))
            {
                List<ShopCartgoods> result = data.getResult();
                shopCartAdapter.addItem(result);
            }
            else {
                Toast.makeText(getContext(), "展示失败"+data.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), "失败"+e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        shopCartGoodsPresenter.reqeust(userId,sessionId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
