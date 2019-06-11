package com.app.raindrops.moviesapi.Mvp;

import com.app.raindrops.moviesapi.Model.Movie;

import java.util.List;

public interface MainActivtyContract
{
    interface view {
        void showMovies(List<Movie> populations);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();
    }

    interface presenter {
        void loadData();
    }
}
