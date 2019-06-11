package com.app.raindrops.moviesapi.Mvp;

import android.util.Log;

import com.app.raindrops.moviesapi.Model.Movie;
import com.app.raindrops.moviesapi.Retrofit.APIInterface;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PresenterImpl implements MainActivtyContract.presenter {


    private APIInterface apiInterface;
    private MainActivtyContract.view view;
    private  Disposable disposable;
    @Inject
    public PresenterImpl(APIInterface apiInterface, MainActivtyContract.view view) {
        this.apiInterface = apiInterface;
        this.view = view;
    }

    @Override
    public void loadData() {
        callapi();
        //For calling api every 5 second we can use Dispossable
        //comment callapi method when uncomment callapiwithdissposable()
       // callapiwithdissposable();
    }

    private void callapi() {
        //Thread management is done automatically in rxjava
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
    private void callapiwithdissposable(){
        //time can be changed by period value
        disposable = Observable.interval(1000, 5000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        //  callEndpoints();
                       // Boko();
                        callapi();
                        Log.e("APIcalled", "Api called");

                    }
                });

        //if user want to end api call
        //disposable.dispose();

    }
}
