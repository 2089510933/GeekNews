package com.dawson.geeknews.base.contract.main;

import com.dawson.geeknews.base.BasePresenter;
import com.dawson.geeknews.base.BaseView;
import com.dawson.geeknews.model.base.WelcomeBean;

/**
 * 作者：Administrator on 2017/8/23 11:54
 * 邮箱：zhangxxx_java@163.com
 */

public interface WelcomeContract {
    interface View extends BaseView{
        void showContent(WelcomeBean welcomeBean);

        void jumpToMain();
    }
    interface  Presenter extends BasePresenter<View> {

        void getWelcomeData();

    }
}
