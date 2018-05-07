package com.iafzal.challenge.samsung.movieapp.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;

import java.util.List;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    LiveData<List<MovieEntity>> loadAllMovies();

    @Query("SELECT * FROM movie")
    LiveData<List<MovieEntity> > selectAllMovies();

    @Query("SELECT * FROM movie where id = :movieID")
    LiveData<MovieEntity> loadMovie(Integer movieID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MovieEntity> pMovieEntityList);

    @Query("DELETE FROM movie")
    void deleteAll();
}
