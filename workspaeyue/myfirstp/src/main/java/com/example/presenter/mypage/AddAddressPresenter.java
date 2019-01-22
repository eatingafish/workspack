package com.example.presenter.mypage;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class AddAddressPresenter extends BasePresenter {
    public AddAddressPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.addAddress((int) args[0],String.valueOf(args[1]),String.valueOf(args[2]),String.valueOf(args[3]),
                String.valueOf(args[4]),String.valueOf(args[5]));
    }
}
