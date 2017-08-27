package com.dawson.geeknews.ui.wechat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dawson.geeknews.R;
import com.dawson.geeknews.component.ImageLoader;
import com.dawson.geeknews.model.base.WXItemBean;
import com.dawson.geeknews.widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangxxx on 2017/8/27.
 */

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WXItemBean> mList;

    public WechatAdapter(Context mContext, List<WXItemBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public WechatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_wechat, parent, false));
    }

    @Override
    public void onBindViewHolder(WechatAdapter.ViewHolder holder, int position) {
        ImageLoader.load(mContext,mList.get(position).getPicUrl(),holder.ivImage);
        holder.tvTitle.setText(mList.get(position).getTitle());
        holder.tvFrom.setText(mList.get(position).getDescription());
        holder.tvTime.setText(mList.get(position).getCtime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_wechat_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_wechat_item_time)
        TextView tvTime;
        @BindView(R.id.tv_wechat_item_from)
        TextView tvFrom;
        @BindView(R.id.iv_wechat_item_image)
        SquareImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
