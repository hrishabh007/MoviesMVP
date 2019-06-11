package com.app.raindrops.moviesapi.Retrofit;

import com.app.raindrops.moviesapi.Model.Movie;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("movies.json")
    Observable<List<Movie>> getMovies();

}
