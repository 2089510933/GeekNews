package com.dawson.geeknews.model.http;

import com.dawson.geeknews.model.WelcomeBean;
import com.dawson.geeknews.model.base.VersionBean;
import com.dawson.geeknews.model.http.response.MyHttpResponse;

import io.reactivex.Flowable;

/**
 * 作者：Administrator on 2017/8/23 17:43
 * 邮箱：zhangxxx_java@163.com
 */

public interface HttpHelper {

    Flowable<WelcomeBean> fetchWelcomeInfo(String res);

    Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo();

//    Flowable<VersionFir> fetchFirVersionInfo();
}
