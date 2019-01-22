package com.example.presenter;

import com.example.http.IResult;
import com.example.http.NetWorkMannger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class LoginPresenter extends BasePresenter{
    public LoginPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IResult iResult = NetWorkMannger.getNetWorkMannger().create(IResult.class);
        return iResult.login(String.valueOf(args[0]),String.valueOf(args[1]));
    }
}
