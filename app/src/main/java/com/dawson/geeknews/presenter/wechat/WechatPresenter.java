package com.dawson.geeknews.presenter.wechat;

import com.dawson.geeknews.base.RxPresenter;
import com.dawson.geeknews.base.contract.wechat.WechatContract;
import com.dawson.geeknews.model.DataManager;
import com.dawson.geeknews.model.base.WXItemBean;
import com.dawson.geeknews.model.http.response.WXHttpResponse;
import com.dawson.geeknews.util.RxUtil;
import com.dawson.geeknews.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by zhangxxx on 2017/8/27.
 */

public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {
    private static final int NUM_OF_PAGE = 20;
    private int currentPage = 1;
    private String queryStr = null;

    private DataManager mDataManager;

    @Inject
    public WechatPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getWechatData() {
        queryStr = null;
        currentPage = 1;

        addSubscribe(mDataManager.fetchWechatListInfo(NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WXItemBean>>(mView) {
                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showContent(wxItemBeen);
                    }
                })
        );
    }
    @Override
    public void getMoreWechatData() {
        Flowable<WXHttpResponse<List<WXItemBean>>> observable;
        if (queryStr != null) {
            observable = mDataManager.fetchWechatSearchListInfo(NUM_OF_PAGE,++currentPage,queryStr);
        } else {
            observable = mDataManager.fetchWechatListInfo(NUM_OF_PAGE,++currentPage);
        }
        addSubscribe(observable
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WXItemBean>>(mView, "没有更多了ヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showMoreContent(wxItemBeen);
                    }
                })
        );
    }
}
