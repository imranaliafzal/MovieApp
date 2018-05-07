package com.iafzal.challenge.samsung.movieapp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iafzal.challenge.samsung.movieapp.R;
import com.iafzal.challenge.samsung.movieapp.SharedResources;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.utils.GenreUtil;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Imran Afzal. All rights reserved.
 */
public class RVMovieAdapter extends RecyclerView.Adapter<RVMovieAdapter.MovieViewHolder>   implements RecyclerView.OnClickListener {

    @Override
    public void onClick(View v) {
        v.getContext();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnItemTouchListener, RecyclerView.OnClickListener{

        LinearLayout mLinearLayoutHorizontal;

        CardView mCardView;
        TextView title;
        TextView popularity;
        TextView genre;
        ImageView mImageView;
        Integer movieId;

        MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            popularity = itemView.findViewById(R.id.tv_popularity);
            genre = itemView.findViewById(R.id.tv_genre);
            mImageView = itemView.findViewById(R.id.iv_movie);
            mCardView = itemView.findViewById(R.id.cardView);
            mLinearLayoutHorizontal = itemView.findViewById(R.id.ll_horizontal);
        }

        @Override
        public void onClick(View v) {
            Intent lIntent = MovieDetailsActivity.newIntent(v.getContext(),  movieId);
            v.getContext().startActivity(lIntent);
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            ;
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            ;
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
        movieViewHolder.movieId = movies.get(i).getId();
        movieViewHolder.title.setText(movies.get(i).getTitle());
        movieViewHolder.popularity.setText("Popularity: "+String.valueOf(movies.get(i).getPopularity()));
        movieViewHolder.genre.setText(GenreUtil.genreNamesFromIdList(movies.get(i).getGenre_ids()));
        String lPosterPath = movies.get(i).getPoster_path();
        SharedResources.getInstance().getWebService().fetchImage(lPosterPath).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // display the image data in a ImageView or save it
                        Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                        movieViewHolder.mImageView.setImageBitmap(bm);
                    } else {
                        // TODO
                    }
                } else {
                    // TODO
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.getCause();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
