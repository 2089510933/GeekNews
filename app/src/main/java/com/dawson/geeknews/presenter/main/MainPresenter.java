package com.dawson.geeknews.presenter.main;

import com.dawson.geeknews.base.RxPresenter;
import com.dawson.geeknews.base.main.MainContract;
import com.dawson.geeknews.model.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * 作者：Administrator on 2017/8/24 16:00
 * 邮箱：zhangxxx_java@163.com
 */

public class MainPresenter extends RxPresenter<MainContract.View> {
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    protected void unSubscribe() {

    }

    @Override
    protected void addSubscribe(Disposable subscription) {
        super.addSubscribe(subscription);
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
