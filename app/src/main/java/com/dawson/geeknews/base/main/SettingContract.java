package com.dawson.geeknews.base.main;

import com.dawson.geeknews.base.BasePresenter;
import com.dawson.geeknews.base.BaseView;
import com.dawson.geeknews.model.base.VersionFir;

/**
 * Created by zhangxxx on 2017/8/26.
 */

public interface SettingContract {
    interface View extends BaseView {

        void showUpdateDialog(VersionFir bean);

    }

    interface  Presenter extends BasePresenter<View> {

        void checkVersion(String currentVersion);

        void setNightModeState(boolean b);

        void setNoImageState(boolean b);

        void setAutoCacheState(boolean b);

        boolean getNightModeState();

        boolean getNoImageState();

        boolean getAutoCacheState();
    }
}
