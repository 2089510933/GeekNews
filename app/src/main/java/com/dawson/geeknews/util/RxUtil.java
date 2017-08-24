package com.dawson.geeknews.util;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Administrator on 2017/8/24 09:33
 * 邮箱：zhangxxx_java@163.com
 */

public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> observable) {
                return observable//RxJava被观察者
                        .subscribeOn(Schedulers.io())//后台运行
                        .observeOn(AndroidSchedulers.mainThread());//UI主线程通知
            }
        };
    }
}
