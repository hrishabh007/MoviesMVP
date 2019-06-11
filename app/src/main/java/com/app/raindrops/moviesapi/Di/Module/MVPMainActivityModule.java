package com.app.raindrops.moviesapi.Di.Module;


import com.app.raindrops.moviesapi.Di.Scope.ActivityScope;
import com.app.raindrops.moviesapi.Mvp.MainActivtyContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPMainActivityModule {
    private MainActivtyContract.view view;

    public MVPMainActivityModule(MainActivtyContract.view view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    MainActivtyContract.view provideView() {
        return view;
    }
}
