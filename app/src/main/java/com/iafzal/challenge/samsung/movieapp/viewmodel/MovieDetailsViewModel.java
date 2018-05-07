package com.iafzal.challenge.samsung.movieapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.iafzal.challenge.samsung.movieapp.MovieApp;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/7/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class MovieDetailsViewModel extends AndroidViewModel{

    LiveData<MovieEntity> mMovieEntityLiveData;

    public LiveData<MovieEntity> getMovieEntityLiveData(Integer movieId) {
        if(mMovieEntityLiveData.getValue() == null){
            mMovieEntityLiveData = ((MovieApp)getApplication()).getDatabase().movieDao().loadMovie(movieId);
        }
        return mMovieEntityLiveData;
    }

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
    }

}
