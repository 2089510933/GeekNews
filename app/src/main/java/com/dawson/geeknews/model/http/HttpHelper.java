package com.dawson.geeknews.model.http;

import com.dawson.geeknews.model.WelcomeBean;
import com.dawson.geeknews.model.base.VersionFir;

import io.reactivex.Flowable;
import retrofit2.Call;

/**
 * 作者：Administrator on 2017/8/23 17:43
 * 邮箱：zhangxxx_java@163.com
 */

public interface HttpHelper {

    Flowable<WelcomeBean> fetchWelcomeInfo(String res);
    Flowable<VersionFir> fetchFirVersionInfo(String api_token);
    Call<VersionFir> fetchFirVersionInfoCall(String api_token);
}
