package com.app.raindrops.moviesapi.Ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.raindrops.moviesapi.Adapter.MovieAdapter;
import com.app.raindrops.moviesapi.CommunUtils.MyApp;
import com.app.raindrops.moviesapi.Di.Component.ApplicationComponent;
import com.app.raindrops.moviesapi.Di.Component.DaggerMainActivityComponent;
import com.app.raindrops.moviesapi.Di.Component.MainActivityComponent;
import com.app.raindrops.moviesapi.Di.Module.MVPMainActivityModule;
import com.app.raindrops.moviesapi.Di.Module.MainActivityContextModule;
import com.app.raindrops.moviesapi.Di.Qualifier.ActivityContext;
import com.app.raindrops.moviesapi.Di.Qualifier.ApplicationContext;
import com.app.raindrops.moviesapi.Model.Movie;
import com.app.raindrops.moviesapi.Mvp.MainActivtyContract;
import com.app.raindrops.moviesapi.Mvp.PresenterImpl;
import com.app.raindrops.moviesapi.R;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ClickListener, MainActivtyContract.view {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    MainActivityComponent mainActivityComponent;

    @Inject
    public MovieAdapter recyclerViewAdapter;


    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ApplicationComponent applicationComponent = MyApp.get(this).getApplicationComponent();

        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mVPMainActivityModule(new MVPMainActivityModule(this))
                .applicationComponent(applicationComponent)
                .build();
        mainActivityComponent.injectMainActivity(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);

        presenter.loadData();
    }

    @Override
    public void launchIntent(Movie name) {
        //Launch intend with movie details or show toast on item click
        Toast.makeText(mContext, "" + name.getTitle(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMovies(List<Movie> populations) {
        recyclerViewAdapter.setData(populations);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
