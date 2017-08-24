package com.dawson.geeknews.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 作者：Administrator on 2017/8/23 17:13
 * 邮箱：zhangxxx_java@163.com
 */


@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
