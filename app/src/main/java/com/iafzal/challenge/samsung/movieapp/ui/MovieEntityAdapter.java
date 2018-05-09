package com.iafzal.challenge.samsung.movieapp.ui;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iafzal.challenge.samsung.movieapp.R;
import com.iafzal.challenge.samsung.movieapp.api.TmdbWebService;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.utils.DateUtil;
import com.iafzal.challenge.samsung.movieapp.utils.GenreUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/8/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class MovieEntityAdapter extends PagedListAdapter<MovieEntity, MovieEntityAdapter.MovieEntityViewHolder> {

    private Context mContext;


    public MovieEntityAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;

    }

    @Override
    public MovieEntityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row_item, parent, false);
        return new MovieEntityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieEntityViewHolder holder, int position) {
        MovieEntity movie = getItem(position);
        if (movie != null) {
            holder.bindTo(movie);
        } else {

            holder.clear();
        }
    }


    public static final DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieEntity oldMovieEntity, @NonNull MovieEntity newMovieEntity) {

            return oldMovieEntity.getId() == newMovieEntity.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieEntity oldMovieEntity, @NonNull MovieEntity newMovieEntity) {

            return oldMovieEntity.equals(newMovieEntity);
        }
    };

    public class MovieEntityViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView title;
        private TextView popularity;
        private TextView genre;
        private ImageView mImageView;
        private TextView releaseDate;
        private CardView mCardView;

        public MovieEntityViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.title = itemView.findViewById(R.id.tv_title);
            this.popularity = itemView.findViewById(R.id.tv_popularity);
            this.genre = itemView.findViewById(R.id.tv_genre);
            this.mImageView = itemView.findViewById(R.id.iv_movie);
            this.releaseDate = itemView.findViewById(R.id.tv_release_date);
            this.mCardView = itemView.findViewById(R.id.cardView);
        }

        public void bindTo(MovieEntity pMovieEntity) {

            title.setText(pMovieEntity.getTitle());
            popularity.setText("Popularity: " + String.valueOf(pMovieEntity.getPopularity()));
            genre.setText(GenreUtil.genreNamesFromIdList(pMovieEntity.getGenre_ids()));
            releaseDate.setText(DateUtil.getInstance().formatShortDate(pMovieEntity.getRelease_date()));
            String lBackdropPath = pMovieEntity.getBackdrop_path();
            TmdbWebService.getInstance().fetchImage("w500",lBackdropPath).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            // display the image data in a ImageView or save it
                            Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                            mImageView.setImageBitmap(bm);
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

            mCardView.setOnClickListener(v ->{
                        Intent i =  MovieDetailsActivity.newIntent(mContext, pMovieEntity);
                        mContext.startActivity(i);
                    });
        }


        public void clear() {
            mImageView.setImageURI(null);
            title.setText(null);
            popularity.setText(null);
            genre.setText(null);
            releaseDate.setText(null);
        }
    }
}
