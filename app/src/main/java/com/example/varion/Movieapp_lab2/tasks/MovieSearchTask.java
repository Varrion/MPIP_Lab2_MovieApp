package com.example.varion.Movieapp_lab2.tasks;

import android.os.AsyncTask;

import com.example.varion.Movieapp_lab2.OmdbAPI;
import com.example.varion.Movieapp_lab2.adapters.MovieAdapter;
import com.example.varion.Movieapp_lab2.model.Movie;
import com.example.varion.Movieapp_lab2.model.ApiSearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by angela on 11/14/17.
 */

public class MovieSearchTask extends AsyncTask<Void, Void, List<Movie>> {

    private OmdbAPI service;
    private MovieAdapter adapter;
    private String searchQuery;

    public MovieSearchTask(MovieAdapter adapter, String searchQuery) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/?")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(OmdbAPI.class);
        this.adapter = adapter;
        this.searchQuery = searchQuery;
    }


    @Override
    protected void onPreExecute() {
        // Common scenario: Start progress dialog
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        adapter.setData(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        Call<ApiSearchResult> call = service.searchMovieByName(searchQuery, "5c54f7e2");
        try {
            ApiSearchResult res = call.execute().body();
            return res.Search;
        } catch (IOException e) {
            System.out.println("TUKA PAGJAT!");
        }
        return new ArrayList<>();
    }
}
