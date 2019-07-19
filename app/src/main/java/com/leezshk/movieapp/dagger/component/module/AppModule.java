package com.leezshk.movieapp.dagger.component.module;

import android.app.Application;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leesa Shakya on 7/17/2019.
 * leezshk@gmail.com
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return mApplication;
    }
}
