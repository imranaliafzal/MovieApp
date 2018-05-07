package com.iafzal.challenge.samsung.movieapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.iafzal.challenge.samsung.movieapp.DataRepository;
import com.iafzal.challenge.samsung.movieapp.MovieApp;
import com.iafzal.challenge.samsung.movieapp.SharedResources;
import com.iafzal.challenge.samsung.movieapp.db.AppDatabase;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.model.DiscoverResponse;
import com.iafzal.challenge.samsung.movieapp.model.MovieReleaseType;
import com.iafzal.challenge.samsung.movieapp.utils.DateUtil;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    public void fetchData(MovieReleaseType pMovieReleaseType){
        Integer page = getPageNum();
        Log.d("Fetch Data: page-{}", String.valueOf(page));

        Callback<DiscoverResponse> lDiscoverResponseCallback = new Callback<DiscoverResponse>() {
            @Override
            public void onResponse(Call<DiscoverResponse> call, Response<DiscoverResponse> response) {

                if(response.isSuccessful()) {
                    Log.d("Response Data: page-{}", String.valueOf(response.body().getPage()));

                    setTotalNumOfPages(response.body().getTotal_pages());

                    List<MovieEntity> movies = response.body().getResults();
                    DataRepository lDataRepository = ((MovieApp) getApplication()).getRepository();
                    lDataRepository.insertMovies(movies);

                }else{
                    //show toast message with error
                }
            }

            @Override
            public void onFailure(Call<DiscoverResponse> call, Throwable t) {
                t.getCause();
            }
        };


        String releaseDateLte;
        String releaseDateGte;

        if(pMovieReleaseType == MovieReleaseType.NOW_PLAYING){
            releaseDateLte = DateUtil.getInstance().formatShortDate(new Date());
            releaseDateGte = DateUtil.getInstance().dateBySubtractingNumOfMonthsFrom(new Date(), 1);
        }else {
            releaseDateLte = DateUtil.getInstance().dateByAddingNumOfMonthsFrom(new Date(), 1);
            releaseDateGte = DateUtil.getInstance().formatShortDate(new Date());
        }

        SharedResources.getInstance().getWebService().discoverMovie(releaseDateGte,releaseDateLte,page).enqueue(lDiscoverResponseCallback);
    }

}
