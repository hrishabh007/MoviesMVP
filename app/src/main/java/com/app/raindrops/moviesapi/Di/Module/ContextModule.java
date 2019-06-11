package com.app.raindrops.moviesapi.Di.Module;

import android.content.Context;

import com.app.raindrops.moviesapi.Di.Qualifier.ApplicationContext;
import com.app.raindrops.moviesapi.Di.Scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }


    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
