package org.bill.logger.app;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

/**
 * 作者：Bill
 * 时间：2018-06-14 17:00
 * 描述：App
 */
public final class App extends Application
{
    public static Context appContext;

    @Override
    public void onCreate()
    {
        // 检测代码
        super.onCreate();

        appContext = this.getApplicationContext();
        //忽略Android N 对URI曝光给其他应用的StrictMode限制
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }
}
