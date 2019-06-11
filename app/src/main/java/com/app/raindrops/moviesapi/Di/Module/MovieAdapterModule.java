package com.app.raindrops.moviesapi.Di.Module;


import com.app.raindrops.moviesapi.Adapter.MovieAdapter;
import com.app.raindrops.moviesapi.Di.Qualifier.ActivityContext;
import com.app.raindrops.moviesapi.Di.Scope.ActivityScope;
import com.app.raindrops.moviesapi.Ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class MovieAdapterModule {
    @Provides
    @ActivityScope
    public MovieAdapter movieAdapter(MovieAdapter.ClickListener clickListener, MainActivity mainActivity) {
        return new MovieAdapter(clickListener, mainActivity);

    }

    @Provides
    @ActivityScope
    public MovieAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
