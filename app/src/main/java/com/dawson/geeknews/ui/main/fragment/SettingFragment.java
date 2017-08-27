package com.dawson.geeknews.ui.main.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawson.geeknews.R;
import com.dawson.geeknews.base.BaseFragment;
import com.dawson.geeknews.base.contract.main.SettingContract;
import com.dawson.geeknews.model.base.VersionFir;
import com.dawson.geeknews.presenter.main.SettingPresenter;
import com.dawson.geeknews.ui.main.activity.MainActivity;

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
        StringBuilder content = new StringBuilder("版本号: v");
        content.append(bean.getVersion());
        content.append("\r\n");
        content.append("版本大小: ");
        content.append(bean.getBinary());
        content.append("\r\n");
        content.append("更新内容:\r\n");
        content.append(bean.getChangelog().replace("\\r\\n","\r\n"));

        //Dialog会话框
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
        builder.setTitle("检测到新版本!");
        builder.setMessage(content);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("马上更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Activity mActivity = getActivity();
                if (mActivity instanceof MainActivity) {
                    ((MainActivity) mActivity).checkPermissions();
                }
            }
        });
        builder.show();
    }
}
