package com.dawson.geeknews.base;

import android.view.View;
import android.view.ViewGroup;

import com.dawson.geeknews.R;
import com.dawson.geeknews.widget.ProgressImageView;

/**
 * Created by zhangxxx on 2017/8/27.
 */

public abstract class RootFragment<T extends BasePresenter> extends BaseFragment<T> {
    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;

    private int currentState = STATE_MAIN;

    private ViewGroup viewMain;
    private ViewGroup mParent;
    private View viewLoading;
    private View viewError;
    private ProgressImageView ivLoading;
    private boolean isErrorViewAdded = false;
    private int mErrorResource = R.layout.view_error;

    @Override
    protected void initEventAndData() {
        if (getView() == null) return;
        viewMain = (ViewGroup) getView().findViewById(R.id.view_main);
        if (viewMain == null) {
            throw new IllegalStateException(
                    "RootActivity的子类必须包含名为“view_main”的视图。");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main的ParentView应该是ViewGroup。");
        }
        mParent = (ViewGroup) viewMain.getParent();
        /**
         * 预设一个加载进度布局视图
         */
        View.inflate(mContext, R.layout.view_progress, mParent);//获取等待加载的布局文件
        viewLoading = mParent.findViewById(R.id.view_loading);
        ivLoading = (ProgressImageView) viewLoading.findViewById(R.id.iv_progress);
        viewLoading.setVisibility(View.GONE);//不可见不保留
        viewMain.setVisibility(View.VISIBLE);//可见
    }

    @Override
    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        if (!isErrorViewAdded) {
            isErrorViewAdded = true;
            View.inflate(mContext, mErrorResource, mParent);
            viewError = mParent.findViewById(R.id.view_error);
            if (viewError == null) {
                throw new IllegalStateException(
                        "视图应在ErrorLayoutResource中命名为“view_error”。");
            }
        }
        hideCurrentView();
        currentState = STATE_ERROR;
        viewError.setVisibility(View.VISIBLE);//可见
    }

    @Override
    public void stateLoading() {
        //当前的状态
        if (currentState == STATE_LOADING) return;
        hideCurrentView();
        currentState = STATE_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
        ivLoading.start();
    }
    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        viewMain.setVisibility(View.VISIBLE);
    }

    //因此当前视图
    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
                viewMain.setVisibility(View.GONE);//不显示
                break;
            case STATE_LOADING:
                ivLoading.stop();
                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                if (viewError != null) {
                    viewError.setVisibility(View.GONE);
                }
                break;
        }
    }
}
