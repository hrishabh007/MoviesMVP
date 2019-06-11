package com.app.raindrops.moviesapi.Mvp;

import com.app.raindrops.moviesapi.Model.Movie;
import com.app.raindrops.moviesapi.Retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterImpl implements MainActivtyContract.presenter {


    private APIInterface apiInterface;
    private MainActivtyContract.view view;

    @Inject
    public PresenterImpl(APIInterface apiInterface, MainActivtyContract.view view) {
        this.apiInterface = apiInterface;
        this.view = view;
    }

    @Override
    public void loadData() {
        apiInterface.getMovies().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Movie>>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showProgress();
            }

            @Override
            public void onNext(List<Movie> movies) {
                view.showMovies(movies);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                view.showComplete();
                view.hideProgress();
            }
        });
    }
}
