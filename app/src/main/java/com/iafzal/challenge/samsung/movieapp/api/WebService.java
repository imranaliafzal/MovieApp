package com.iafzal.challenge.samsung.movieapp.api;

import com.iafzal.challenge.samsung.movieapp.model.DiscoverResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/5/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public interface WebService {

    @GET ("discover/movie")
    Call<DiscoverResponse> discoverMovie();

    @GET("discover/movie")
    Call<DiscoverResponse> discoverMovie(@Query("page") int page);
}
