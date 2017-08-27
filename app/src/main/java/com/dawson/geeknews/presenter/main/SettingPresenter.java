package com.dawson.geeknews.presenter.main;

import com.dawson.geeknews.base.RxPresenter;
import com.dawson.geeknews.base.contract.main.SettingContract;
import com.dawson.geeknews.model.DataManager;
import com.dawson.geeknews.model.base.VersionFir;
import com.dawson.geeknews.util.LogUtil;
import com.dawson.geeknews.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by zhangxxx on 2017/8/26.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public SettingPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void checkVersion(final String currentVersion) {
        String api_token = "1b4fa24326017c903c9faa50dc00ef9d";
//        Call<VersionFir> call = mDataManager.fetchFirVersionInfoCall(api_token);
//        call.enqueue(new Callback<VersionFir>() {
//            @Override
//            public void onResponse(Call<VersionFir> call, Response<VersionFir> response) {
//                Log.e(TAG, "normalGet:" + response.body().getChangelog()+ "");
//            }
//            @Override
//            public void onFailure(Call<VersionFir> call, Throwable t) {
//
//            }
//        });
        //方式二
        addSubscribe(mDataManager.fetchFirVersionInfo(api_token).
                compose(RxUtil.<VersionFir>rxSchedulerHelper()).
                subscribe(getConsumeWelcomeBean(currentVersion), getConsumeThrowable()));

    }
    private Consumer<VersionFir> getConsumeWelcomeBean(final String currentVersion) {
        return new Consumer<VersionFir>() {
            @Override
            public void accept(VersionFir versionFir) {
                LogUtil.i("Version:" + versionFir.getVersion() + " Changelog:" +versionFir.getChangelog());
//                if (Integer.valueOf(currentVersion.replace(".", "")) < Integer.valueOf(versionFir.getVersion().replace(".", ""))) {
                    mView.showUpdateDialog(versionFir);
//                } else {
//                    mView.showErrorMsg("已经是最新版本~");
//                }
            }
        };
    }
    private Consumer<Throwable> getConsumeThrowable() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                mView.showErrorMsg("获取版本信息失败 T T");
            }
        };
    }

    @Override
    public void setNightModeState(boolean b) {

    }

    @Override
    public void setNoImageState(boolean b) {

    }

    @Override
    public void setAutoCacheState(boolean b) {

    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public boolean getNoImageState() {
        return false;
    }

    @Override
    public boolean getAutoCacheState() {
        return false;
    }
}
