package com.app.raindrops.moviesapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.raindrops.moviesapi.Model.Movie;
import com.app.raindrops.moviesapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    List<Movie> movies;
    private MovieAdapter.ClickListener clickListener;
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
        // String boko= String.valueOf(movies.get(position).getReleaseYear());

        ArrayList<String> list = new ArrayList<>();
        list.addAll(movies.get(position).getGenre());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
            stringBuilder.append(",");

        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        holder.genre.setText(stringBuilder.toString());
        holder.releaseYear.setText(String.valueOf(movies.get(position).getReleaseYear()));
        //holder.genre.setText(movies.get(position).getGenre());
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
        ImageView thumbnail;
        TextView title, rating, genre, releaseYear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            genre = (TextView) itemView.findViewById(R.id.genre);
            releaseYear = (TextView) itemView.findViewById(R.id.releaseYear);
        }
    }
}
