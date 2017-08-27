package com.dawson.geeknews.di.component;

import com.dawson.geeknews.di.module.FragmentModule;
import com.dawson.geeknews.di.scope.FragmentScope;
import com.dawson.geeknews.ui.main.fragment.SettingFragment;
import com.dawson.geeknews.ui.wechat.fragment.WechatMainFragment;

import dagger.Component;

/**
 * Created by zhangxxx on 2017/8/26.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(SettingFragment settingFragment);
    void inject(WechatMainFragment settingFragment);
}
