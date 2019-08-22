package com.ryan.movieapplication.RetroFit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIHandler {

    @GET("movie?sort_by=popularity.desc&api_key=405919c16153d844293dd52d0cab1256")
    Call<Movie> getPopularMovies();

    @GET("https://api.themoviedb.org/3/discover/movie?api_key=405919c16153d844293dd52d0cab1256")
    Call<Movie> getVoteAverage(@Query("vote_average.gte") double average);
}
