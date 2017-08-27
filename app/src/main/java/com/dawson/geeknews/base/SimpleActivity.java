package com.dawson.geeknews.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dawson.geeknews.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：Administrator on 2017/8/23 09:45
 * 邮箱：zhangxxx_java@163.com
 *
 * 继承fragmentation框架的SupportActivity，便于activity生命周期管理
 */
public abstract class SimpleActivity extends SupportActivity {
    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

    protected void onViewCreated() {
    }

    /**
     * 获取xml布局文件ID
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化事件和数据
     */
    protected abstract void initEventAndData();
}
