package com.iafzal.challenge.samsung.movieapp.repository;

import android.arch.lifecycle.MutableLiveData;

import okhttp3.ResponseBody;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/5/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
public class MovieRepository {

    public MutableLiveData<ResponseBody> discoverMovies(){

        final MutableLiveData<ResponseBody> data = new MutableLiveData<>();

    /*    SharedResources.getInstance().getWebService().discoverMovie().enqueue(new Callback<DiscoverResponse>() {

            @Override
            public void onResponse(Call<DiscoverResponse> call, Response<DiscoverResponse> response) {
                DiscoverResponse lDiscoverResponse = response.body();
            }

            @Override
            public void onFailure(Call<DiscoverResponse> call, Throwable t) {
                t.getCause();
            }
        });
*/
        return data;
    }
}
