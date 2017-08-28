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

import com.dawson.geeknews.util.ToastUtil;

import java.io.File;

/**
 * Created by zhangxxx on 2017/8/27.
 */

public class UpdateService extends Service {

    String APK_DOWNLOAD_URL = "http://download.fir.im/v2/app/install/59a046b9ca87a81949000129?download_token=155c6b53c54f5cc353e777cbf3a64bd0&source=update";


    private BroadcastReceiver receiver;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //注册广播接收者，监听下载状态
        receiver =  new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                unregisterReceiver(receiver);
                // http://blog.csdn.net/pi9nc/article/details/9407899
                //下载到本地后执行安装http://www.jianshu.com/p/6816977bfdeb
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//任务堆的启动模式
                //将下载的更新包放在移动设备上指定的目录
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() +
                                "/download/geeknews.apk")),
                        "application/vnd.android.package-archive");
                startActivity(intent);
                stopSelf();
            }
        };
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        //下载
        startDownload(APK_DOWNLOAD_URL,"geeknew.apk");
        return Service.START_STICKY;
    }

    /**
     * 自带下载器DownloadManager
     */
    private void startDownload(String install_url,String name) {
        //创建下载任务
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(MyApis.APK_DOWNLOAD_URL));
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(install_url));
        request.setTitle("GeekNews");
        request.setDescription("新版本下载中");
        request.setMimeType("application/vnd.android.package-archive");
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "geeknew.apk");//设置下载的路径
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);//设置下载的路径

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        //系统下载器
        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        dm.enqueue(request);
        ToastUtil.shortShow("后台下载中，请稍候...");
    }

}
