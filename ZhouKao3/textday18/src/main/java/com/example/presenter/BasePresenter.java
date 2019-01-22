package com.example.presenter;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {
    private Consumer consumer;

    public BasePresenter(Consumer consumer) {
        this.consumer = consumer;
    }

    public void UnBind()
    {
        this.consumer = null;
    }

    protected abstract Observable observable(Object...args);
    public void Request(Object...args)
    {
        observable(args).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }
}
