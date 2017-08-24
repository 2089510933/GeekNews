package com.dawson.geeknews.base;


import com.dawson.geeknews.app.App;
import com.dawson.geeknews.di.component.ActivityComponent;
import com.dawson.geeknews.di.component.DaggerActivityComponent;
import com.dawson.geeknews.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * 作者：Administrator on 2017/8/23 10:01
 * 邮箱：zhangxxx_java@163.com
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject//此类(目标类)标注了T,说明依赖的T类
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }
    /**
     * 获取activiyt module对象
     * @return
     */
    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);//构造模型对象
    }
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            //附加视图
            mPresenter.attachView(this);
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            //分离视图
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }
    protected abstract void initInject();
}
