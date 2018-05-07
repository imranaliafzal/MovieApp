package com.iafzal.challenge.samsung.movieapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iafzal.challenge.samsung.movieapp.R;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;

import java.util.List;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class RVMovieAdapter extends RecyclerView.Adapter<RVMovieAdapter.MovieViewHolder> {

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView movieId;
        TextView releaseDate;
        TextView overView;

        MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            movieId = itemView.findViewById(R.id.tv_id);
            releaseDate = itemView.findViewById(R.id.tv_date_released);
            overView = itemView.findViewById(R.id.tv_over_view);
        }
    }

    List<MovieEntity> movies;

    RVMovieAdapter(List<MovieEntity> movies){
        this.movies = movies;
    }

    public void appendMovies(List<MovieEntity> pMovieEntityList){
        this.movies.clear();
        this.movies.addAll(pMovieEntityList);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_row_item, viewGroup, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.title.setText(movies.get(i).getTitle());
        movieViewHolder.movieId.setText(String.valueOf(movies.get(i).getId()));
        movieViewHolder.releaseDate.setText(String.valueOf(movies.get(i).getRelease_date().getTime()));
        movieViewHolder.overView.setText(movies.get(i).getOverview());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
