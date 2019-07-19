package com.leezshk.movieapp.dagger.component;

import com.leezshk.movieapp.activities.MovieListActivity;
import com.leezshk.movieapp.dagger.component.module.AppModule;
import com.leezshk.movieapp.dagger.component.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Leesa Shakya on 7/17/2019.
 * leezshk@gmail.com
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(MovieListActivity movieListActivity);
}
