package com.dawson.geeknews.model;


import com.dawson.geeknews.model.base.VersionBean;
import com.dawson.geeknews.model.http.HttpHelper;
import com.dawson.geeknews.model.http.response.MyHttpResponse;
import com.dawson.geeknews.model.prefs.PreferencesHelper;

import io.reactivex.Flowable;

/**
 * 作者：Administrator on 2017/8/23 17:02
 * 邮箱：zhangxxx_java@163.com
 */

public class DataManager implements HttpHelper, PreferencesHelper {
    HttpHelper mHttpHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper mHttpHelper, PreferencesHelper preferencesHelper) {
        this.mHttpHelper = mHttpHelper;
        this.mPreferencesHelper = preferencesHelper;
    }

    /**
     * @param res
     * @return
     */
    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }

    @Override
    public boolean getVersionPoint() {
        return mPreferencesHelper.getVersionPoint();
    }

    @Override
    public void setVersionPoint(boolean isFirst) {

        mPreferencesHelper.setVersionPoint(isFirst);
    }

    @Override
    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        return mHttpHelper.fetchVersionInfo();
    }
}
