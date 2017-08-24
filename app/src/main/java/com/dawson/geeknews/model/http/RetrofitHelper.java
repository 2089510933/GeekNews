package com.dawson.geeknews.model.http;

import com.dawson.geeknews.model.WelcomeBean;
import com.dawson.geeknews.model.base.VersionBean;
import com.dawson.geeknews.model.http.api.MyApis;
import com.dawson.geeknews.model.http.api.ZhihuApis;
import com.dawson.geeknews.model.http.response.MyHttpResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * 作者：Administrator on 2017/8/23 18:00
 * 邮箱：zhangxxx_java@163.com
 */

public class RetrofitHelper implements HttpHelper {

    private ZhihuApis mZhihuApiService;
    private MyApis mMyApiService;

    /**
     * 创建RetrofitHelper对象
     * @param mZhihuApiService
     */
    @Inject
    public RetrofitHelper(ZhihuApis mZhihuApiService,MyApis myApiService) {
        this.mZhihuApiService = mZhihuApiService;
        myApiService = myApiService;
    }



    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }

    @Override
    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        return mMyApiService.getVersionInfo();
    }
}
