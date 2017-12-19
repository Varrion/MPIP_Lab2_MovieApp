package com.example.varion.Movieapp_lab2;

import com.example.varion.Movieapp_lab2.model.Movie;
import com.example.varion.Movieapp_lab2.model.ApiSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface OmdbAPI {

    @GET("/")
    Call<ApiSearchResult> searchMovieByName(@Query("s") String name,
                                            @Query("apikey") String apiKey);

    @GET("/")
    Call<Movie> getMovieById(@Query("i") String id,
                             @Query("apikey") String apiKey,
                             @Query("plot") String plotType);

}
