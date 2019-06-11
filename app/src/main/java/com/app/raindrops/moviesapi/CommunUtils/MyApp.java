package com.app.raindrops.moviesapi.CommunUtils;

import android.app.Activity;
import android.app.Application;


import com.app.raindrops.moviesapi.Di.Component.ApplicationComponent;
import com.app.raindrops.moviesapi.Di.Component.DaggerApplicationComponent;
import com.app.raindrops.moviesapi.Di.Module.ContextModule;



public class MyApp extends Application {
    public ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
    }

    public static MyApp get(Activity activity){
        return (MyApp) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
