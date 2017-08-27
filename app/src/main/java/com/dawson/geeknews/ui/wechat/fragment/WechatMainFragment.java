package com.dawson.geeknews.ui.wechat.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dawson.geeknews.R;
import com.dawson.geeknews.base.RootFragment;
import com.dawson.geeknews.base.contract.wechat.WechatContract;
import com.dawson.geeknews.model.base.WXItemBean;
import com.dawson.geeknews.presenter.wechat.WechatPresenter;
import com.dawson.geeknews.ui.wechat.adapter.WechatAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangxxx on 2017/8/24.
 */

public class WechatMainFragment extends RootFragment<WechatPresenter> implements WechatContract.View{
    @BindView(R.id.view_main)
    RecyclerView rvWechatList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;



    WechatAdapter mAdapter;
    List<WXItemBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.view_common_list;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mList = new ArrayList<>();
        mAdapter = new WechatAdapter(mContext,mList);
        rvWechatList.setLayoutManager(new LinearLayoutManager(mContext));
        rvWechatList.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                mPresenter.getWechatData();
            }
        });
        //stateLoading();
        mPresenter.getWechatData();
    }
    @Override
    public void showContent(List<WXItemBean> list) {
        //关闭 滑动刷新控件
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<WXItemBean> mList) {

    }


}
