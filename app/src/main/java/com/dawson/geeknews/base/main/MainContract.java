package com.dawson.geeknews.base.main;

import com.dawson.geeknews.base.BaseView;

/**
 * 作者：Administrator on 2017/8/24 16:01
 * 邮箱：zhangxxx_java@163.com
 */

public interface MainContract {
    interface View extends BaseView {

        void showUpdateDialog(String versionContent);

        void startDownloadService();
    }

    public interface Presenter {
        //检查版本
        void checkVersion(String currentVersion);
        //得到版本点
        boolean getVersionPoint();
        //设置版本点
        void setVersionPoint(boolean b);
    }
}
