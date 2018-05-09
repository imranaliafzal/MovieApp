package com.iafzal.challenge.samsung.movieapp.api;

import com.iafzal.challenge.samsung.movieapp.model.DiscoverResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/5/18.
 * Copyright © 2018 Imran Afzal. All rights reserved.
 */
public interface WebService {

    @GET("http://image.tmdb.org/t/p/{size}/{poster_path}")
    Call<ResponseBody> fetchImage(
                @Path(value="size", encoded = true) String size,
                                      @Path(value = "poster_path", encoded = true) String poster_path);

    @GET("/4/discover/movie")
    Call<DiscoverResponse> discoverMovie(@Query("primary_release_date.gte") String primaryReleaseDateGte, @Query("primary_release_date.lte") String primaryReleaseDateLte, @Query("page") int page);

    @GET("/4/discover/movie")
    Call<DiscoverResponse> discoverMovie(@Query(value = "page") int page);

}
