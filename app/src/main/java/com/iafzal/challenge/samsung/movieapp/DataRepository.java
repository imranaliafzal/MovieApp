package com.iafzal.challenge.samsung.movieapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.iafzal.challenge.samsung.movieapp.db.AppDatabase;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;

import java.util.List;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private MediatorLiveData<List<MovieEntity>> mObservableMovies;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableMovies = new MediatorLiveData<>();

        mObservableMovies.addSource(mDatabase.movieDao().loadAllMovies(),
                movieEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableMovies.postValue(movieEntities);
                    }
                });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);

                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of movies from the database and get notified when the data changes.
     */
    public LiveData<List<MovieEntity>> getMovies() {
        return mObservableMovies;
    }


}
