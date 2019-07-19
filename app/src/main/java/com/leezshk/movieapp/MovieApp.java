package com.leezshk.movieapp;

import android.app.Application;
import android.content.Context;

import com.leezshk.movieapp.dagger.component.AppComponent;
import com.leezshk.movieapp.dagger.component.DaggerAppComponent;
import com.leezshk.movieapp.dagger.component.module.AppModule;
import com.leezshk.movieapp.dagger.component.module.NetModule;

/**
 * Created by Leesa Shakya on 7/17/2019.
 * leezshk@gmail.com
 */
public class MovieApp extends Application{
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .netModule(new NetModule())
                    .build();
        }

        return appComponent;
    }

    public static MovieApp getMyApplication(Context context){
        return (MovieApp) context.getApplicationContext();
    }
}
