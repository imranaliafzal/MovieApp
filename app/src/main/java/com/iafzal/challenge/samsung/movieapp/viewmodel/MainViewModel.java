package com.iafzal.challenge.samsung.movieapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.iafzal.challenge.samsung.movieapp.MovieApp;
import com.iafzal.challenge.samsung.movieapp.db.AppDatabase;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;

import java.util.List;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class MainViewModel extends AndroidViewModel {

    private Integer pageNum = 0;

    private Integer totalNumOfPages = 1;

    public LiveData<List<MovieEntity>> getMovieEntityList() {
        AppDatabase lDatabase = ((MovieApp)getApplication()).getDatabase();
        return lDatabase.movieDao().selectAllMovies();
    }


    public MainViewModel(@NonNull Application application) {
        super(application);
        pageNum = 1;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pPageNum) {
        pageNum = pPageNum;
    }

    public Integer getTotalNumOfPages() {
        return totalNumOfPages;
    }

    public void setTotalNumOfPages(Integer pTotalNumOfPages) {
        totalNumOfPages = pTotalNumOfPages;
    }

    /*
    SharedResources.getInstance().getWebService().discoverMovie().enqueue(new Callback<DiscoverResponse>() {
            @Override
            public void onResponse(Call<DiscoverResponse> call, Response<DiscoverResponse> response) {
                List<MovieEntity> movies = response.body().getResults();
                AppDatabase database = ((MovieApp)getApplication()).getDatabase();

                Executors.newSingleThreadExecutor().execute(()->{
                    database.runInTransaction(() -> database.movieDao().insertAll(movies));
                    LiveData<List<MovieEntity>> lListLiveData = database.movieDao().loadAllMovies();
                    lListLiveData.getValue();
                    List<MovieEntity> lMovieEntities = database.movieDao().selectAllMovies();
                    lMovieEntities.size();
                });



            }

            @Override
            public void onFailure(Call<DiscoverResponse> call, Throwable t) {
                t.getCause();
            }
        });

        LiveData<List<MovieEntity>> lListLiveData = ((MovieApp)getApplication()).getRepository().getMovies();
        lListLiveData.getValue();
     */

}
