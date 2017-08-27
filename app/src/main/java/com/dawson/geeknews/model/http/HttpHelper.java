package com.dawson.geeknews.model.http;

import com.dawson.geeknews.model.base.WelcomeBean;
import com.dawson.geeknews.model.base.VersionFir;
import com.dawson.geeknews.model.base.WXItemBean;
import com.dawson.geeknews.model.http.response.WXHttpResponse;

import java.util.List;

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
    Flowable<WXHttpResponse<List<WXItemBean>>> fetchWechatListInfo(int num, int page);
}
