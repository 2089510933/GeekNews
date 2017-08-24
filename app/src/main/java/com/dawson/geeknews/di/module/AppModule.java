package com.dawson.geeknews.di.module;

import com.dawson.geeknews.app.App;
import com.dawson.geeknews.model.DataManager;
import com.dawson.geeknews.model.http.HttpHelper;
import com.dawson.geeknews.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：Administrator on 2017/8/23 16:54
 * 邮箱：zhangxxx_java@163.com
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}
