package com.dawson.geeknews.ui.main.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawson.geeknews.R;
import com.dawson.geeknews.base.BaseFragment;
import com.dawson.geeknews.base.main.SettingContract;
import com.dawson.geeknews.model.base.VersionFir;
import com.dawson.geeknews.presenter.main.SettingPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangxxx on 2017/8/24.
 */

public class SettingFragment  extends BaseFragment<SettingPresenter> implements SettingContract.View{
    @BindView(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;
    @BindView(R.id.tv_setting_update)
    TextView tvSettingUpdate;

    private String versionName;
    @OnClick(R.id.ll_setting_update)
    void doUpdate() {
        mPresenter.checkVersion(versionName);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initEventAndData() {
        try {
            PackageManager pm = getActivity().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getActivity().getPackageName(), PackageManager.GET_ACTIVITIES);
            versionName = pi.versionName;
            tvSettingUpdate.setText(String.format("当前版本号 v%s",versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showUpdateDialog(VersionFir bean) {

    }
}
