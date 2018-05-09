package com.iafzal.challenge.samsung.movieapp.repo.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.iafzal.challenge.samsung.movieapp.api.TmdbWebService;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/8/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class TmdbNetworkDataSourceFactory extends DataSource.Factory<Integer, MovieEntity> {

    public MutableLiveData<TmdbNetworkDataSource> dataSourceLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Integer, MovieEntity> create() {
        TmdbNetworkDataSource dataSource = new TmdbNetworkDataSource(TmdbWebService.getInstance());
        dataSourceLiveData.postValue(dataSource);
        return dataSource;
    }
}
