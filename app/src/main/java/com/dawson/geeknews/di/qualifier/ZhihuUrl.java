package com.dawson.geeknews.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 作者：Administrator on 2017/8/24 10:37
 * 邮箱：zhangxxx_java@163.com
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ZhihuUrl {

}
