package com.leezshk.movieapp.dagger.component.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leezshk.movieapp.BuildConfig;
import com.leezshk.movieapp.api.Endpoints;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.leezshk.movieapp.api.Endpoints.API_BASE_URL;

/**
 * Created by Leesa Shakya on 7/17/2019.
 * leezshk@gmail.com
 */

@Module
public class NetModule {
    @Provides
    @Singleton
    OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder  okHttpClient=  new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES);

        okHttpClient.interceptors().add(logging);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    Retrofit getRetrofit(OkHttpClient httpClient){
        return new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    Endpoints getWebService(Retrofit retrofit) {
        return retrofit.create(Endpoints.class);
    }

//    @Provides
//    @Singleton
//    Gson provideGson(){
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//        return gsonBuilder.create();
//    }
}
