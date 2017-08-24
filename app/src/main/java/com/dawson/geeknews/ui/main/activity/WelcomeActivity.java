package com.dawson.geeknews.ui.main.activity;

import android.util.Log;

import com.dawson.geeknews.R;
import com.dawson.geeknews.base.BaseActivity;
import com.dawson.geeknews.base.main.WelcomeContract;
import com.dawson.geeknews.model.WelcomeBean;
import com.dawson.geeknews.presenter.main.WelcomePresenter;


public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @Override
    protected void initInject() {
        getActivityComponent().inject(WelcomeActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        //p层：执行数据处理
        mPresenter.getWelcomeData();
    }

    /**
     * 显示内容
     * @param welcomeBean
     */
    @Override
    public void showContent(WelcomeBean welcomeBean) {

        Log.e(">>","showContent()");
    }

    /**
     * 跳转
     */
    @Override
    public void jumpToMain() {

        Log.e(">>","jumpToMain()");
    }
}
