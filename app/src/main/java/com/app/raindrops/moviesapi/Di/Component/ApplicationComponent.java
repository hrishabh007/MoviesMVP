package com.app.raindrops.moviesapi.Di.Component;


import android.content.Context;


import com.app.raindrops.moviesapi.CommunUtils.MyApp;
import com.app.raindrops.moviesapi.Di.Module.ContextModule;
import com.app.raindrops.moviesapi.Di.Module.RetrofitModule;
import com.app.raindrops.moviesapi.Di.Qualifier.ApplicationContext;
import com.app.raindrops.moviesapi.Di.Scope.ApplicationScope;
import com.app.raindrops.moviesapi.Retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApp myApplication);
}
