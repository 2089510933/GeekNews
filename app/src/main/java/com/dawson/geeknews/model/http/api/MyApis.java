package com.dawson.geeknews.model.http.api;


import com.dawson.geeknews.model.base.VersionFir;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by codeest on 16/10/10.
 * https://github.com/codeestX/my-restful-api
 */

public interface MyApis {

    //String HOST = "http://api.fir.im/apps/latest/59a046b9ca87a81949000129?api_token=1b4fa24326017c903c9faa50dc00ef9d/";
    String HOST = "http://api.fir.im/apps/latest/";
//    String APK_DOWNLOAD_URL = "http://fir.im/nf8h";
    String APK_DOWNLOAD_URL = "http://download.fir.im/v2/app/install/59a046b9ca87a81949000129?download_token=155c6b53c54f5cc353e777cbf3a64bd0&source=update";

    /**
     * 获取最新版本信息
     *
     * @return
     */
    @GET("59a046b9ca87a81949000129")
    Flowable<VersionFir> getFirVersionInfo(@Query("api_token") String api_token);

    @GET("59a046b9ca87a81949000129")
    Call<VersionFir> getFirVersionInfoCall(@Query("api_token") String api_token);

}
