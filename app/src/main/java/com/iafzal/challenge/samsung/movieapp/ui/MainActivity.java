package com.iafzal.challenge.samsung.movieapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iafzal.challenge.samsung.movieapp.DataRepository;
import com.iafzal.challenge.samsung.movieapp.MovieApp;
import com.iafzal.challenge.samsung.movieapp.R;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.model.MovieReleaseType;
import com.iafzal.challenge.samsung.movieapp.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel mMainViewModel;

    RecyclerView mRecyclerView;

    RVMovieAdapter adapter;

    TabLayout mTabLayout;

    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tabLayout);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                DataRepository lDataRepository = ((MovieApp)getApplication()).getRepository();
                lDataRepository.deleteAllMovies();

                if(mMainViewModel.getMovieEntityList() != null && mMainViewModel.getMovieEntityList().getValue() != null){
                    mMainViewModel.getMovieEntityList().getValue().clear();
                }

                mMainViewModel.setPageNum(1);
                mMainViewModel.setTotalNumOfPages(1);
                adapter.notifyDataSetChanged();
                fetchData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



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
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mMainViewModel.getMovieEntityList().observe(this, movieListObserver);

        mRecyclerView = findViewById(R.id.rv_movieList);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    Integer pNum =  mMainViewModel.getPageNum()+1;
                    if(pNum <= mMainViewModel.getTotalNumOfPages())
                    mMainViewModel.setPageNum(pNum);
                    fetchData();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchData();
    }

    private void fetchData(){
        MovieReleaseType lMovieReleaseType = (mTabLayout.getSelectedTabPosition() == 0)? MovieReleaseType.NOW_PLAYING:MovieReleaseType.COMING_SOON;
        mMainViewModel.fetchData(lMovieReleaseType);
    }

}
