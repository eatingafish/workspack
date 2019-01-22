package com.example.presenter.mypage;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class MyWalletPresenter extends BasePresenter {
    public MyWalletPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.wallet((int)args[0],String.valueOf(args[1]),(int) args[2],(int) args[3]);
    }
}
