package com.dawson.geeknews.di.component;

import com.dawson.geeknews.app.App;
import com.dawson.geeknews.di.module.AppModule;
import com.dawson.geeknews.di.module.HttpModule;
import com.dawson.geeknews.model.DataManager;
import com.dawson.geeknews.model.http.HttpHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者：Administrator on 2017/8/23 10:53
 * 邮箱：zhangxxx_java@163.com
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context
    DataManager getDataManager(); //数据中心
    HttpHelper retrofitHelper(); //数据中心
}
