package com.dawson.geeknews.presenter.main;

import com.dawson.geeknews.base.RxPresenter;
import com.dawson.geeknews.base.main.WelcomeContract;
import com.dawson.geeknews.model.DataManager;
import com.dawson.geeknews.model.WelcomeBean;
import com.dawson.geeknews.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 作者：Administrator on 2017/8/23 11:36
 * 邮箱：zhangxxx_java@163.com
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {
    private static final String RES = "1080*1776";//物件


    private DataManager mDataManager;

    @Inject
    public WelcomePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getWelcomeData() {
        //添加订阅
        addSubscribe(mDataManager.fetchWelcomeInfo(RES)
                .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())//定制类型转换
                .subscribe(getConsumeWelcomeBean(), getConsumeThrowable()));

    }

    private Consumer<WelcomeBean> getConsumeWelcomeBean() {
        return new Consumer<WelcomeBean>() {
            @Override
            public void accept(WelcomeBean welcomeBean) {
                mView.showContent(welcomeBean);
                //startCountDown();
            }
        };
    }

    private Consumer<Throwable> getConsumeThrowable() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                mView.jumpToMain();
            }
        };
    }
}
