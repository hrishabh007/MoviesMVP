package com.app.raindrops.moviesapi.Di.Component;

import android.content.Context;

import com.app.raindrops.moviesapi.Di.Module.MVPMainActivityModule;
import com.app.raindrops.moviesapi.Di.Module.MovieAdapterModule;
import com.app.raindrops.moviesapi.Di.Qualifier.ActivityContext;
import com.app.raindrops.moviesapi.Di.Scope.ActivityScope;
import com.app.raindrops.moviesapi.Ui.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = {MovieAdapterModule.class, MVPMainActivityModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    @ActivityContext
    Context getContext();

    void injectMainActivity(MainActivity mainActivity);
}
