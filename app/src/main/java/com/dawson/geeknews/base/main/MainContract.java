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
}
