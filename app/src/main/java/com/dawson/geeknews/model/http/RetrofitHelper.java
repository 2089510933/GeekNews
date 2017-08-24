package com.dawson.geeknews.model.http;

import com.dawson.geeknews.model.WelcomeBean;
import com.dawson.geeknews.model.http.api.ZhihuApis;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * 作者：Administrator on 2017/8/23 18:00
 * 邮箱：zhangxxx_java@163.com
 */

public class RetrofitHelper implements HttpHelper {

    private ZhihuApis mZhihuApiService;

    /**
     * 创建RetrofitHelper对象
     * @param mZhihuApiService
     */
    @Inject
    public RetrofitHelper(ZhihuApis mZhihuApiService) {
        this.mZhihuApiService = mZhihuApiService;
    }



    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }
}
