package com.dawson.geeknews.base;

/**
 * 作者：Administrator on 2017/8/23 09:58
 * 邮箱：zhangxxx_java@163.com
 *
 * Presenter基类
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
