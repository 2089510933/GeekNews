package com.dawson.geeknews.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.dawson.geeknews.app.App;
import com.dawson.geeknews.app.Constants;

import javax.inject.Inject;

/**
 * Created by zhangxxx on 2017/8/24.
 */

public class ImplPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mSPrefs;//偏好设置
    private static final String SHAREDPREFERENCES_NAME = "my_sp";
    private static final boolean DEFAULT_VERSION_POINT = false;
    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }
    @Override
    public boolean getVersionPoint() {
        return mSPrefs.getBoolean(Constants.SP_VERSION_POINT, DEFAULT_VERSION_POINT);
    }
    @Override
    public void setVersionPoint(boolean isFirst) {
        mSPrefs.edit().putBoolean(Constants.SP_VERSION_POINT, isFirst).apply();
    }

}
