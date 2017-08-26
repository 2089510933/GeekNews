package com.dawson.geeknews.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.dawson.geeknews.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangxxx on 2017/8/26.
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
