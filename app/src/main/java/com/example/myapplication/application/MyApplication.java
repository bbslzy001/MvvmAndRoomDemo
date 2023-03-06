package com.example.myapplication.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * 创建 Application 子类，并持有全局 Context
 *
 * 将 Context 设置为静态变量容易产生内存泄漏的风险（Activity或其他组件的生命周期比应用程序短）
 * 在该子类中，获取的是 Application 的 Context，它的生命周期和应用程序的生命周期一样长，不会造成内存泄漏问题
 * 使用压制警告注解让 IDE 忽略 StaticFieldLeak 警告
 */
public class MyApplication extends Application
{
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext()
    {
        return context;
    }
}
