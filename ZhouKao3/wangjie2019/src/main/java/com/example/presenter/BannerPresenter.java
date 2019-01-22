package com.example.presenter;

import com.example.core.IRequest;
import com.example.core.NetWorkMannger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
/**
 * 时间：2019/1/14
 * 作者:王洁
 * 功能：Banner
 */
public class BannerPresenter extends BasePresenter{
    public BannerPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkMannger.getNetWorkMannger().create(IRequest.class);
        return iRequest.banner();
    }
}
