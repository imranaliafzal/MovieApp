package com.iafzal.challenge.samsung.movieapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iafzal.challenge.samsung.movieapp.model.DiscoverResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/8/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class TmdbWebService implements WebService {
    private static final String baseUrl = "https://api.themoviedb.org/4/";

    private static final String accessToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NGEwYTg1MmI4ODEzZmRlNGM3OTBhZGRmOGQxN2RlZCIsInN1YiI6IjVhZWRmNzVjYzNhMzY4MzQ2MTAwYTNkZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.L9Zsj0-ti9ryomY5NbxpBjpJpyWX3J4OwUpm29Qkpho";

    private WebService webService;

    private static final TmdbWebService ourInstance = new TmdbWebService();

    public static TmdbWebService getInstance() {
        return ourInstance;
    }

    private TmdbWebService() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(chain -> {
            Request originalRequest = chain.request();

            Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                    accessToken);

            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        webService = retrofit.create(WebService.class);
    }

    @Override
    public Call<ResponseBody> fetchImage(String size, String poster_path) {
        return webService.fetchImage(size, poster_path);
    }

    @Override
    public Call<DiscoverResponse> discoverMovie(String primaryReleaseDateGte, String primaryReleaseDateLte, int page) {
        return webService.discoverMovie(primaryReleaseDateGte, primaryReleaseDateLte, page);
    }

    @Override
    public Call<DiscoverResponse> discoverMovie(int page) {
        return webService.discoverMovie(page);
    }

}
