package com.iafzal.challenge.samsung.movieapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.iafzal.challenge.samsung.movieapp.repo.MovieEntityRepository;
import com.iafzal.challenge.samsung.movieapp.repo.MovieEntityRepositoryImpl;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.model.DataLoadStateType;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/7/18.
 * Copyright © 2018 Imran Afzal. All rights reserved.
 */
public class MovieDetailsViewModel extends AndroidViewModel{

    private MovieEntityRepository repository;


    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieEntityRepositoryImpl();
    }

    public LiveData<PagedList<MovieEntity>> getMovies() {
        return repository.getMovies();
    }

    public LiveData<DataLoadStateType> dataLoadStatus() {
        return repository.getDataLoadStatus();
    }

}
