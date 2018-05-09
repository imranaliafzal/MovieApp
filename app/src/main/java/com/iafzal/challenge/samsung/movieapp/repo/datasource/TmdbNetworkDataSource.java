package com.iafzal.challenge.samsung.movieapp.repo.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.iafzal.challenge.samsung.movieapp.api.WebService;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.model.DataLoadStateType;
import com.iafzal.challenge.samsung.movieapp.model.DiscoverResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/8/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class TmdbNetworkDataSource extends PageKeyedDataSource<Integer, MovieEntity> {

    private WebService mWebService;

    public final MutableLiveData<DataLoadStateType> loadState;

    protected TmdbNetworkDataSource(WebService pDataProvider) {
        mWebService = pDataProvider;
        loadState = new MutableLiveData<DataLoadStateType>();
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MovieEntity> callback) {
        loadState.postValue(DataLoadStateType.LOADING);

        Call<DiscoverResponse> request = mWebService.discoverMovie(1);

        Response<DiscoverResponse> response = null;
        try{
            response = request.execute();
            if(response != null && response.isSuccessful()) {
                callback.onResult(response.body().getResults(), 1, 2);
            }else {
                callback.onResult(null, null,2);
            }

            loadState.postValue(DataLoadStateType.LOADED);
        }catch (IOException ex) {
            loadState.postValue(DataLoadStateType.FAILED);
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieEntity> callback) {
        loadState.postValue(DataLoadStateType.LOADING);
        Call<DiscoverResponse> request = mWebService.discoverMovie(params.key);

        Response<DiscoverResponse> response = null;
        try{
            response = request.execute();
            if(response != null && response.isSuccessful()) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                callback.onResult(response.body().getResults(), adjacentKey);
            }else {
                callback.onResult(null, params.key - 1);
            }
            loadState.postValue(DataLoadStateType.LOADED);
        }catch (IOException ex) {
            //networkState.postValue();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieEntity> callback) {
        loadState.postValue(DataLoadStateType.LOADING);

        Call<DiscoverResponse> request = mWebService.discoverMovie(params.key);

        Response<DiscoverResponse> response = null;
        try{
            response = request.execute();
            if(response != null && response.isSuccessful()) {
                callback.onResult(response.body().getResults(), params.key + 1);
            }else {
                callback.onResult(null, params.key + 1 );
            }
            loadState.postValue(DataLoadStateType.LOADED);
        }catch (IOException ex) {
            //networkState.postValue();
            loadState.postValue(DataLoadStateType.FAILED);
        }
    }
}
