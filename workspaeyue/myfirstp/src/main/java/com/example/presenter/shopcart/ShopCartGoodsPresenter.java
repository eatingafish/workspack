package com.example.presenter.shopcart;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class ShopCartGoodsPresenter extends BasePresenter {
    public ShopCartGoodsPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.shopgoods((long)args[0],String.valueOf(args[1]));
    }
}
