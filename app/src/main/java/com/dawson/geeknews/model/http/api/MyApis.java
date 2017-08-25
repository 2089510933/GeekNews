package com.dawson.geeknews.model.http.api;


import com.dawson.geeknews.model.base.VersionBean;
import com.dawson.geeknews.model.http.response.MyHttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by codeest on 16/10/10.
 * https://github.com/codeestX/my-restful-api
 */

public interface MyApis {

    String HOST = "http://codeest.me/api/geeknews/";
//    String HOST = "http://api.fir.im/apps/latest/59a046b9ca87a81949000129?api_token=1b4fa24326017c903c9faa50dc00ef9d";

    String APK_DOWNLOAD_URL = "http://codeest.me/apk/geeknews.apk";
//    String APK_DOWNLOAD_URL = "https://fir.im/nf8h";

    /**
     * 获取最新版本信息
     * @return
     */
    @GET("version")
    Flowable<MyHttpResponse<VersionBean>> getVersionInfo();
//    @GET("version")
//    Flowable<VersionFir> getFirVersionInfo();

}
