package com.iafzal.challenge.samsung.movieapp.repo;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.MainThread;

import com.iafzal.challenge.samsung.movieapp.repo.datasource.TmdbNetworkDataSourceFactory;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.model.DataLoadStateType;
import com.iafzal.challenge.samsung.movieapp.utils.AppsExecutor;

import static android.arch.lifecycle.Transformations.switchMap;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/8/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class MovieEntityRepositoryImpl implements MovieEntityRepository {

    TmdbNetworkDataSourceFactory dataSourceFactory;
    private static final int PAGE_SIZE = 20;
    private LiveData<PagedList<MovieEntity>> movies;

    public MovieEntityRepositoryImpl() {
        dataSourceFactory = new TmdbNetworkDataSourceFactory();
    }

    @Override
    @MainThread
    public LiveData<PagedList<MovieEntity>> getMovies() {

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build();


        movies = new LivePagedListBuilder(dataSourceFactory, config)
                .setInitialLoadKey(1).setFetchExecutor(AppsExecutor.networkIO())
                .build();

        return movies;
    }

    @Override
    public LiveData<DataLoadStateType> getDataLoadStatus(){
        return switchMap(dataSourceFactory.dataSourceLiveData,
                dataSource -> dataSource.loadState);
    }
}
