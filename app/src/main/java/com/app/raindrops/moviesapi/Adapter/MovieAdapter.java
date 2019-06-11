package com.app.raindrops.moviesapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.raindrops.moviesapi.Model.Movie;
import com.app.raindrops.moviesapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> movies;
    private ClickListener clickListener;
    private Context context;

    @Inject
    public MovieAdapter(ClickListener clickListener, Context context) {
        this.clickListener = clickListener;
        this.context = context;
        movies = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(movies.get(position).getImage()).into(holder.thumbnail);
        holder.title.setText(movies.get(position).getTitle());
        holder.rating.setText(String.valueOf(movies.get(position).getRating()));

        //Arraylist addAll()' call can be replaced with parametrized constructor

        ArrayList<String> list = new ArrayList<>(movies.get(position).getGenre());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
            stringBuilder.append(",");

        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        holder.genre.setText(stringBuilder.toString());
        holder.releaseYear.setText(String.valueOf(movies.get(position).getReleaseYear()));

        //Java 8 lambda
        holder.movierow.setOnClickListener((View v) -> {
            // do something here
            clickListener.launchIntent(movies.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface ClickListener {
        void launchIntent(Movie name);
    }

    public void setData(List<Movie> data) {
        this.movies.addAll(data);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.rating)
        TextView rating;
        @BindView(R.id.genre)
        TextView genre;
        @BindView(R.id.releaseYear)
        TextView releaseYear;
        @BindView(R.id.movierow)
        RelativeLayout movierow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
