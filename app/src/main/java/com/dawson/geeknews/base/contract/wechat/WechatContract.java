package com.dawson.geeknews.base.contract.wechat;

import com.dawson.geeknews.base.BasePresenter;
import com.dawson.geeknews.base.BaseView;
import com.dawson.geeknews.model.base.WXItemBean;

import java.util.List;

/**
 * Created by zhangxxx on 2017/8/27.
 */

public interface WechatContract {
    interface View extends BaseView {

        void showContent(List<WXItemBean> mList);

        void showMoreContent(List<WXItemBean> mList);
    }

    interface Presenter extends BasePresenter<View> {

        void getWechatData();

        void getMoreWechatData();
    }
}
