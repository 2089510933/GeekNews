package com.dawson.geeknews.base;

/**
 * 作者：Administrator on 2017/8/23 09:54
 * 邮箱：zhangxxx_java@163.com
 *
 * View基类
 */

public interface BaseView {
    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State 状态 =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
