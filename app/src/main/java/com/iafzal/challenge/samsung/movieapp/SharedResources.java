package com.iafzal.challenge.samsung.movieapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iafzal.challenge.samsung.movieapp.api.WebService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class SharedResources {


    private static final SharedResources ourInstance = new SharedResources();


    private WebService webService;

    private static final String baseUrl = "https://api.themoviedb.org/4/";

    private static final String accessToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NGEwYTg1MmI4ODEzZmRlNGM3OTBhZGRmOGQxN2RlZCIsInN1YiI6IjVhZWRmNzVjYzNhMzY4MzQ2MTAwYTNkZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.L9Zsj0-ti9ryomY5NbxpBjpJpyWX3J4OwUpm29Qkpho";

    public static SharedResources getInstance() {
        return ourInstance;
    }

    private SharedResources() {
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

    public WebService getWebService() {
        return webService;
    }

}
