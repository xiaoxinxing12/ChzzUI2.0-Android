package org.chzz.demo;

import android.app.Application;

import org.chzz.demo.engine.Engine;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/6/21 下午10:13
 * 描述:
 */
public class App extends Application {
    private static App sInstance;
    private Engine mEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mEngine = new Retrofit.Builder()
                .baseUrl("http://www.chzz.org:8081/detail/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }

    public static App getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }

}