package com.leezshk.movieapp.activities;

import com.leezshk.movieapp.dto.Movie;

import java.util.List;

/**
 * Created by Leesa Shakya on 7/18/2019.
 * leezshk@gmail.com
 */
public interface MovieListView {
    void getMovieListSuccess(List<Movie> movieResponse);
    void getMovieListFailure();
    void showLoading();
    void hideLoading();
}
