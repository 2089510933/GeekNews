package com.dawson.geeknews.app;

import android.app.Activity;
import android.app.Application;

import com.dawson.geeknews.di.component.AppComponent;
import com.dawson.geeknews.di.component.DaggerAppComponent;
import com.dawson.geeknews.di.module.AppModule;
import com.dawson.geeknews.di.module.HttpModule;

import java.util.HashSet;
import java.util.Set;


public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;
    public static AppComponent appComponent;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }

    /**
     * 添加
     * @param act
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除
     * @param act
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

}
