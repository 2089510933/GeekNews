package com.dawson.geeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dawson.geeknews.app.App;
import com.dawson.geeknews.di.component.DaggerFragmentComponent;
import com.dawson.geeknews.di.component.FragmentComponent;
import com.dawson.geeknews.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by zhangxxx on 2017/8/26.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView{
    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        //SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}
