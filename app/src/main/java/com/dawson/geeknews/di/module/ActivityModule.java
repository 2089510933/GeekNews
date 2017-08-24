package com.dawson.geeknews.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：Administrator on 2017/8/23 10:08
 * 邮箱：zhangxxx_java@163.com
 * Dagger2框架
 */
@Module //表明该类提供依赖
public class ActivityModule {
    private Activity mActivity;
    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }
    @Provides//告诉Dagger我们需要构造对象并且提供对象
    public Activity provideActivity(){return mActivity;}
}
