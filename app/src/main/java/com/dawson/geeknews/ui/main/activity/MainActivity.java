package com.dawson.geeknews.ui.main.activity;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dawson.geeknews.R;
import com.dawson.geeknews.app.App;
import com.dawson.geeknews.app.Constants;
import com.dawson.geeknews.base.BaseActivity;
import com.dawson.geeknews.base.main.MainContract;
import com.dawson.geeknews.presenter.main.MainPresenter;
import com.dawson.geeknews.ui.gank.GankMainFragment;
import com.dawson.geeknews.ui.gold.GoldMainFragment;
import com.dawson.geeknews.ui.main.fragment.AboutFragment;
import com.dawson.geeknews.ui.main.fragment.LikeFragment;
import com.dawson.geeknews.ui.main.fragment.SettingFragment;
import com.dawson.geeknews.ui.vtex.VtexMainFragment;
import com.dawson.geeknews.ui.wechat.WechatMainFragment;
import com.dawson.geeknews.ui.zhuhu.ZhihuMainFragment;
import com.dawson.geeknews.util.SystemUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

import static com.dawson.geeknews.app.Constants.TYPE_ZHIHU;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();
    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.view_search)
    MaterialSearchView mSearchView;
    //Drawer侧拉抽屉
    ActionBarDrawerToggle mDrawerToggle;
    // Fragment
    ZhihuMainFragment mZhihuFragment;
    GankMainFragment mGankFragment;
    WechatMainFragment mWechatFragment;
    GoldMainFragment mGoldFragment;
    VtexMainFragment mVtexFragment;
    LikeFragment mLikeFragment;
    SettingFragment mSettingFragment;
    AboutFragment mAboutFragment;
    // 标识符
    private int showFragment = TYPE_ZHIHU;//显示的Fragment标识常量
    private int hideFragment = TYPE_ZHIHU;//隐藏的Fragment标识常量
    MenuItem mLastMenuItem;
    MenuItem mSearchMenuItem;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "知乎日报");
        mZhihuFragment = new ZhihuMainFragment();
        mGankFragment = new GankMainFragment();
        mWechatFragment = new WechatMainFragment();
        mGoldFragment = new GoldMainFragment();
        mVtexFragment = new VtexMainFragment();
        mLikeFragment = new LikeFragment();
        mSettingFragment = new SettingFragment();
        mAboutFragment = new AboutFragment();
        //导航栏监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();//监听同步
        //最后的菜单选项
        mLastMenuItem = mNavigationView.getMenu().findItem(R.id.drawer_zhihu);
        //加载多个根片段
        loadMultipleRootFragment(R.id.fl_main_content, 0,
                mZhihuFragment, mGankFragment, mWechatFragment, mGoldFragment,
                mAboutFragment, mLikeFragment, mSettingFragment, mVtexFragment);
        mNavigationView.setNavigationItemSelectedListener(this);//导航栏的监听
        //版本更新操作
        if (!mPresenter.getVersionPoint() && SystemUtil.isWifiConnected()) {
            mPresenter.setVersionPoint(true);
            try {
                //版本对比
                PackageManager pm = getPackageManager();
                PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
                String versionName = pi.versionName;//当前版本号
               // mPresenter.checkVersion(versionName);//更新版本
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            showExitDialog();
        }
    }

    private void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出GeekNews吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }

    @Override
    public void showUpdateDialog(String versionContent) {

    }

    @Override
    public void startDownloadService() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.drawer_zhihu:
                showFragment = Constants.TYPE_ZHIHU;
                mSearchMenuItem.setVisible(false);
                break;
            case R.id.drawer_gank:
                showFragment = Constants.TYPE_GANK;
                mSearchMenuItem.setVisible(true);
                break;
            case R.id.drawer_wechat:
                showFragment = Constants.TYPE_WECHAT;
                mSearchMenuItem.setVisible(true);
                break;
            case R.id.drawer_gold:
                showFragment = Constants.TYPE_GOLD;
                mSearchMenuItem.setVisible(false);
                break;
            case R.id.drawer_vtex:
                showFragment = Constants.TYPE_VTEX;
                mSearchMenuItem.setVisible(false);
                break;
            case R.id.drawer_setting:
                showFragment = Constants.TYPE_SETTING;
                mSearchMenuItem.setVisible(false);
                break;
            case R.id.drawer_like:
                showFragment = Constants.TYPE_LIKE;
                mSearchMenuItem.setVisible(false);
                break;
            case R.id.drawer_about:
                showFragment = Constants.TYPE_ABOUT;
                mSearchMenuItem.setVisible(false);
                break;
        }
        if (mLastMenuItem != null) {
            mLastMenuItem.setChecked(false);
        }
        mLastMenuItem = menuItem;
        // mPresenter.setCurrentItem(showFragment);
        menuItem.setChecked(true);
        mToolbar.setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
        //替换Fragment
        showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
        hideFragment = showFragment;//互换Fragment标识
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加入搜索Menu
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        mSearchView.setMenuItem(item);
        mSearchMenuItem = item;
        return true;
    }

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return mZhihuFragment;
            case Constants.TYPE_GANK:
                return mGankFragment;
            case Constants.TYPE_WECHAT:
                return mWechatFragment;
            case Constants.TYPE_GOLD:
                return mGoldFragment;
            case Constants.TYPE_VTEX:
                return mVtexFragment;
            case Constants.TYPE_LIKE:
                return mLikeFragment;
            case Constants.TYPE_SETTING:
                return mSettingFragment;
            case Constants.TYPE_ABOUT:
                return mAboutFragment;
        }
        return mZhihuFragment;
    }
}
