package com.dawson.geeknews.di.component;

import com.dawson.geeknews.di.module.ActivityModule;
import com.dawson.geeknews.di.scope.ActivityScope;
import com.dawson.geeknews.ui.main.activity.WelcomeActivity;

import dagger.Component;

/**
 * 作者：Administrator on 2017/8/23 10:19
 * 邮箱：zhangxxx_java@163.com
 * Dagger2 注射器,连接被注入的类和依赖类的地方
 * 自定义作用域ActivityScope
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules= ActivityModule.class)
public interface ActivityComponent {

    void inject(WelcomeActivity welcomeActivity);
}
