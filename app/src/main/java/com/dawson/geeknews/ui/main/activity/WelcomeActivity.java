package com.dawson.geeknews.ui.main.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawson.geeknews.R;
import com.dawson.geeknews.base.BaseActivity;
import com.dawson.geeknews.base.contract.main.WelcomeContract;
import com.dawson.geeknews.component.ImageLoader;
import com.dawson.geeknews.model.base.WelcomeBean;
import com.dawson.geeknews.presenter.main.WelcomePresenter;

import butterknife.BindView;


public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;

    @Override
    protected void initInject() {
        getActivityComponent().inject(WelcomeActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        //p层：执行数据处理
        mPresenter.getWelcomeData();
    }

    /**
     * 显示内容
     *
     * @param welcomeBean
     */
    @Override
    public void showContent(WelcomeBean welcomeBean) {
        //Log.e(">>","showContent()");
        ImageLoader.load(this, welcomeBean.getImg(), ivWelcomeBg);
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        tvWelcomeAuthor.setText(welcomeBean.getText());
    }

    /**
     * 跳转
     */
    @Override
    public void jumpToMain() {
        //Log.e(">>","jumpToMain()");
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
