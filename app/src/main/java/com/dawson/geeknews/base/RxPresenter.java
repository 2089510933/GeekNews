package com.dawson.geeknews.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：Administrator on 2017/8/23 11:37
 * 邮箱：zhangxxx_java@163.com
 *
 * Rxjava异步框架
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;
    //Disposable资源管理器
    protected CompositeDisposable mCompositeDisposable;

    /**
     * 销毁
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 添加订阅
     * @param subscription
     */
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }


    @Override
    public void attachView(T view) {

        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
