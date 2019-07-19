package com.leezshk.movieapp.api;

import com.leezshk.movieapp.dto.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leesa Shakya on 7/17/2019.
 * leezshk@gmail.com
 */
public interface Endpoints {
    String API_BASE_URL = "https://api.themoviedb.org/3/";
    String GET_MOVIE_LIST = "movie/popular";

    @GET(API_BASE_URL + GET_MOVIE_LIST)
    Call<MovieResponse> getMovieList(
            @Query("api_key") String apiKey

    );

}
