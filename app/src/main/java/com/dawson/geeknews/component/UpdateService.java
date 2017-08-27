package com.dawson.geeknews.component;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.dawson.geeknews.model.http.api.MyApis;
import com.dawson.geeknews.util.ToastUtil;

import java.io.File;

/**
 * Created by zhangxxx on 2017/8/27.
 */

public class UpdateService extends Service {
    private BroadcastReceiver receiver;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //创建广播
        receiver =  new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                unregisterReceiver(receiver);
                /**
                 * http://blog.csdn.net/pi9nc/article/details/9407899
                 * 下载
                 */
                intent = new Intent(Intent.ACTION_VIEW);//根据用户的数据类型打开相应的Activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//任务堆的启动模式
                //将下载的更新包放在移动设备上指定的目录
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/geeknews.apk")),
                        "application/vnd.android.package-archive");
                startActivity(intent);
                stopSelf();
            }
        };
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        startDownload();
        return Service.START_STICKY;
    }
    private void startDownload() {
        //Android手机下载服务管理器
        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(MyApis.APK_DOWNLOAD_URL));
        request.setTitle("GeekNews");
        request.setDescription("新版本下载中");
        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "geeknews.apk");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        dm.enqueue(request);
        ToastUtil.shortShow("后台下载中，请稍候...");
    }
}
