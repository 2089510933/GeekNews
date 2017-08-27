package com.dawson.geeknews.model.http.api;

import com.dawson.geeknews.model.base.WelcomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：Administrator on 2017/8/24 10:20
 * 邮箱：zhangxxx_java@163.com
 *
 * 知乎APIs
 *
 * 使用Retrofit2网络请求框架
 */

public interface ZhihuApis {
    String HOST = "http://news-at.zhihu.com/api/4/";
    /**
     * 启动界面图片
     */
    @GET("start-image/{res}")
    Flowable<WelcomeBean> getWelcomeInfo(@Path("res") String res);
}
