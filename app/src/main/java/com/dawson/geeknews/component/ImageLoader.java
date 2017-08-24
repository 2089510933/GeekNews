package com.dawson.geeknews.component;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * 作者：Administrator on 2017/8/24 16:24
 * 邮箱：zhangxxx_java@163.com
 */

public class ImageLoader {
    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
    public static void load(Activity activity, String url, ImageView iv) {
        if(!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }
}
