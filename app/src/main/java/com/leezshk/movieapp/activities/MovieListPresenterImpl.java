package com.leezshk.movieapp.activities;

import android.support.annotation.NonNull;

import com.leezshk.movieapp.api.Endpoints;
import com.leezshk.movieapp.constants.Constants;
import com.leezshk.movieapp.dto.Movie;
import com.leezshk.movieapp.dto.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leesa Shakya on 7/18/2019.
 * leezshk@gmail.com
 */
public class MovieListPresenterImpl implements MovieListPresenter {
    private final MovieListActivity activity;
    private final Endpoints endpoints;

    MovieListPresenterImpl(MovieListActivity activity, Endpoints endpoints) {

        this.activity = activity;
        this.endpoints = endpoints;
    }

    @Override
    public void getMovieList() {
        activity.showLoading();
        endpoints.getMovieList(Constants.API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call,
                                   @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    activity.hideLoading();
                    MovieResponse movieResponse = response.body();

                    if (movieResponse == null){
                        activity.getMovieListFailure();
                        return;
                    }

                    if (movieResponse.getMovies() == null){
                        activity.getMovieListFailure();
                        return;
                    }

                    List<Movie> movies = movieResponse.getMovies();
                    activity.getMovieListSuccess(movies);
                    for (Movie movie1 : movies) {
                        System.out.println("movie name: " + movie1.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                activity.hideLoading();
                activity.getMovieListFailure();
            }
        });
    }
}
