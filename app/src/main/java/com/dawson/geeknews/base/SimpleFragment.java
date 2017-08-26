package com.dawson.geeknews.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhangxxx on 2017/8/24.
 *
 * 无MVP的Fragment基类
 */

public abstract class SimpleFragment extends SupportFragment {

    protected Activity mActivity;
    protected Context mContext;
    protected View mView;
    private Unbinder mUnBinder;
    protected boolean isInited = false;//是否初始化了

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(getLayoutId(),null);
        return mView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);//添加控件注解框架
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initEventAndData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();//注销
    }

    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
}
