package com.dawson.geeknews.model;

import com.dawson.geeknews.model.http.HttpHelper;

import io.reactivex.Flowable;

/**
 * 作者：Administrator on 2017/8/23 17:02
 * 邮箱：zhangxxx_java@163.com
 */

public class DataManager implements HttpHelper{
    HttpHelper mHttpHelper;

    public DataManager(HttpHelper mHttpHelper) {
        this.mHttpHelper = mHttpHelper;
    }

    /**
     *
     * @param res
     * @return
     */
    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }
}
