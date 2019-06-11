package com.app.raindrops.moviesapi.Di.Module;

import android.content.Context;

import com.app.raindrops.moviesapi.Di.Qualifier.ActivityContext;
import com.app.raindrops.moviesapi.Di.Scope.ActivityScope;
import com.app.raindrops.moviesapi.Ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {

    private MainActivity mainActivity;
    private Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }


    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}
