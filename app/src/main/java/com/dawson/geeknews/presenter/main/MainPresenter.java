package com.dawson.geeknews.presenter.main;

import android.Manifest;
import android.util.Log;

import com.dawson.geeknews.base.RxPresenter;
import com.dawson.geeknews.base.contract.main.MainContract;
import com.dawson.geeknews.model.DataManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 作者：Administrator on 2017/8/24 16:00
 * 邮箱：zhangxxx_java@163.com
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    protected void unSubscribe() {

    }

    @Override
    protected void addSubscribe(Disposable subscription) {
        super.addSubscribe(subscription);
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();//注册活动
    }

    private void registerEvent() {
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void checkVersion(final String currentVersion) {
        Log.e("","checkVersion()");
      /*addSubscribe(mDataManager.fetchFirVersionInfo()
                .compose(RxUtil.<VersionFir>rxSchedulerHelper())
//                .compose(RxUtil.<VersionFir>handleMyResultFir())
                .filter(new Predicate<VersionFir>() {
                    @Override
                    public boolean test(@NonNull VersionFir versionBean) throws Exception {
                        return Integer.valueOf(currentVersion.replace(".", "")) < Integer.valueOf(versionBean.getVersion().replace(".", ""));
                    }
                })
                .map(new Function<VersionFir, String>() {
                    @Override
                    public String apply(VersionFir bean) {
                        StringBuilder content = new StringBuilder("版本号: v");
                        content.append(bean.getVersion());
                        content.append("\r\n");
                        content.append("版本大小: ");
                        content.append(bean.getBinary());
                        content.append("\r\n");
                        content.append("更新内容:\r\n");
                        content.append(bean.getChangelog().replace("\\r\\n","\r\n"));
                        return content.toString();
                    }
                })
                .subscribeWith(new CommonSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showUpdateDialog(s);
                    }
                })
        );*/
    }

    @Override
    public boolean getVersionPoint() {
        return mDataManager.getVersionPoint();
    }

    @Override
    public void setVersionPoint(boolean b) {
        mDataManager.setVersionPoint(b);
    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) {
                        if (granted) {
                            mView.startDownloadService();
                        } else {
                            mView.showErrorMsg("下载应用需要文件写入权限哦~");
                        }
                    }
                })
        );
    }
}
