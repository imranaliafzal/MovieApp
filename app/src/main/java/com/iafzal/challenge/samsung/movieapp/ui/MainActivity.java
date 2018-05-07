package com.iafzal.challenge.samsung.movieapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.iafzal.challenge.samsung.movieapp.MovieApp;
import com.iafzal.challenge.samsung.movieapp.R;
import com.iafzal.challenge.samsung.movieapp.SharedResources;
import com.iafzal.challenge.samsung.movieapp.db.AppDatabase;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.model.DiscoverResponse;
import com.iafzal.challenge.samsung.movieapp.viewmodel.MainViewModel;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MainViewModel mMainViewModel;

    RecyclerView mRecyclerView;

    RVMovieAdapter adapter;

    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Observer<List<MovieEntity>> movieListObserver
                = pListMovieEntity -> {
                        if(adapter == null) {
                            adapter = new RVMovieAdapter(pListMovieEntity);
                            mRecyclerView.setAdapter(adapter);
                        }else {
                            adapter.appendMovies(pListMovieEntity);
                        }

                        adapter.notifyDataSetChanged();

                };

        mMainViewModel = new MainViewModel(getApplication());

        mMainViewModel.getMovieEntityList().observe(this, movieListObserver);

        mRecyclerView = findViewById(R.id.rv_movieList);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    Integer pNum =  mMainViewModel.getPageNum()+1;
                    if(pNum <= mMainViewModel.getTotalNumOfPages())
                    mMainViewModel.setPageNum(pNum);
                    fetchData(mMainViewModel.getPageNum());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchData(mMainViewModel.getPageNum());
    }

    public void fetchData(Integer page){

        Log.d("Fetch Data: page-{}", String.valueOf(page));

        SharedResources.getInstance().getWebService().discoverMovie(page).enqueue(new Callback<DiscoverResponse>() {
            @Override
            public void onResponse(Call<DiscoverResponse> call, Response<DiscoverResponse> response) {

                Log.d("Response Data: page-{}", String.valueOf(response.body().getPage()));

                mMainViewModel.setTotalNumOfPages(response.body().getTotal_pages());

                List<MovieEntity> movies = response.body().getResults();
                AppDatabase database = ((MovieApp)getApplication()).getDatabase();

                Executors.newSingleThreadExecutor().execute(()->{
                    database.runInTransaction(() -> database.movieDao().insertAll(movies));
                });

            }

            @Override
            public void onFailure(Call<DiscoverResponse> call, Throwable t) {
                t.getCause();
            }
        });
    }
}
