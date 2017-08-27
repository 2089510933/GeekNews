package com.dawson.geeknews.di.module;

import com.dawson.geeknews.di.qualifier.MyUrl;
import com.dawson.geeknews.di.qualifier.ZhihuUrl;
import com.dawson.geeknews.di.scope.WechatUrl;
import com.dawson.geeknews.model.http.api.MyApis;
import com.dawson.geeknews.model.http.api.WeChatApis;
import com.dawson.geeknews.model.http.api.ZhihuApis;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Administrator on 2017/8/23 16:58
 * 邮箱：zhangxxx_java@163.com
 */
@Module
public class HttpModule {


    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (true) {
            //Http记录拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            builder.addInterceptor(loggingInterceptor);
        }

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    /**
     * 创建Retrofit2网络请求基类
     *
     * @param builder
     * @param client
     * @param url
     * @return
     */
    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)//当请求的一个完整的网络接口时，baseUrl这里只是传入根路径
                .client(client)//传入OkHttpClient网络请求
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * =======================================================
     */
    @Singleton
    @Provides
    WeChatApis provideWechatService(@WechatUrl Retrofit retrofit) {
        return retrofit.create(WeChatApis.class);
    }
    @Singleton
    @Provides
    @WechatUrl
    Retrofit provideWechatRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, WeChatApis.HOST);
    }

    @Singleton
    @Provides
    MyApis provideMyService(@MyUrl Retrofit retrofit) {
        return retrofit.create(MyApis.class);
    }

    @Singleton
    @Provides
    @MyUrl
    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, MyApis.HOST);
    }

    //用Retorfit创建ZhihuApis的网络请求实列
    @Singleton
    @Provides
    @ZhihuUrl
    Retrofit provideZhihuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhihuApis.HOST);
    }


    //用Retrofit创建一个ZhihuApis的代理对象
    @Singleton
    @Provides
    ZhihuApis provideZhihuService(@ZhihuUrl Retrofit retrofit) {
        return retrofit.create(ZhihuApis.class);
    }
}
