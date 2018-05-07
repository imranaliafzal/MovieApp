package com.iafzal.challenge.samsung.movieapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.iafzal.challenge.samsung.movieapp.R;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.viewmodel.MovieDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    Integer movieId;

    MovieDetailsViewModel mMovieDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        retrieveExtras();

        mMovieDetailsViewModel = new MovieDetailsViewModel(getApplication());
        mMovieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);

        LiveData<MovieEntity> lMovieEntityLiveData = mMovieDetailsViewModel.getMovieEntityLiveData(movieId);
        lMovieEntityLiveData.observe(this, pMovieEntity -> {
            TextView tvDescription = findViewById(R.id.tv_description);
            tvDescription.setText(pMovieEntity.getOverview());
        });
    }

    private void retrieveExtras() {
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            if (extras.containsKey("com.iafzal.challenge.samsung.movieapp.movieId")) {
                this.movieId = extras.getInt("com.iafzal.challenge.samsung.movieapp.movieId");
            }
        }
    }

    public static Intent newIntent(Context packageContext, Integer movieId){
        Intent i = new Intent(packageContext, MovieDetailsActivity.class);
        i.putExtra("com.iafzal.challenge.samsung.movieapp.movieId", movieId);
        return i;
    }




}
