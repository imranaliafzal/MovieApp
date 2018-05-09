package com.iafzal.challenge.samsung.movieapp.repo;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.model.DataLoadStateType;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/8/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public interface MovieEntityRepository {
    LiveData<PagedList<MovieEntity>> getMovies();
    LiveData<DataLoadStateType> getDataLoadStatus();
}
