package com.dawson.geeknews.model.http;

import com.dawson.geeknews.app.Constants;
import com.dawson.geeknews.model.base.WelcomeBean;
import com.dawson.geeknews.model.base.VersionFir;
import com.dawson.geeknews.model.base.WXItemBean;
import com.dawson.geeknews.model.http.api.MyApis;
import com.dawson.geeknews.model.http.api.WeChatApis;
import com.dawson.geeknews.model.http.api.ZhihuApis;
import com.dawson.geeknews.model.http.response.WXHttpResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import retrofit2.Call;

/**
 * 作者：Administrator on 2017/8/23 18:00
 * 邮箱：zhangxxx_java@163.com
 */

public class RetrofitHelper implements HttpHelper {

    private ZhihuApis mZhihuApiService;
    private MyApis mMyApiService;
    private WeChatApis mWechatApiService;
    /**
     * 创建RetrofitHelper对象
     * @param mZhihuApiService
     */
    @Inject
    public RetrofitHelper(ZhihuApis mZhihuApiService,MyApis myApiService,WeChatApis mWechatApiService) {
        this.mZhihuApiService = mZhihuApiService;
        this.mMyApiService = myApiService;
        this.mWechatApiService = mWechatApiService;
    }



    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }

    @Override
    public Flowable<VersionFir> fetchFirVersionInfo(String api_token) {
        return mMyApiService.getFirVersionInfo(api_token);
    }

    @Override
    public Call<VersionFir> fetchFirVersionInfoCall(String api_token) {
        return mMyApiService.getFirVersionInfoCall(api_token);
    }

    @Override
    public Flowable<WXHttpResponse<List<WXItemBean>>> fetchWechatListInfo(int num, int page) {
        return mWechatApiService.getWXHot(Constants.KEY_API, num, page);
    }

    @Override
    public Flowable<WXHttpResponse<List<WXItemBean>>> fetchWechatSearchListInfo(int num, int page, String word) {
        return mWechatApiService.getWXHotSearch(Constants.KEY_API, num, page, word);
    }


}
