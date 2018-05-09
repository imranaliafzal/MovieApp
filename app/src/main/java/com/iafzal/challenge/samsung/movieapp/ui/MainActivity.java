package com.iafzal.challenge.samsung.movieapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.iafzal.challenge.samsung.movieapp.R;
import com.iafzal.challenge.samsung.movieapp.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MovieListActivity";
    private MainViewModel mainViewModel;
    private MovieEntityAdapter mAdapter;

    private ProgressBar mLoadProgressBar;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tabLayout);

        mLoadProgressBar = findViewById(R.id.progress_bar);

        View recyclerView = findViewById(R.id.movie_list);
        assert recyclerView != null;


        setupRecyclerView((RecyclerView) recyclerView);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.dataLoadStatus().observe(this, loadStatus -> {
            switch (loadStatus) {
                case LOADING:
                    mLoadProgressBar.setVisibility(View.VISIBLE);
                    break;
                case LOADED:
                    mLoadProgressBar.setVisibility(View.GONE);
                    break;
                case FAILED:
                    mLoadProgressBar.setVisibility(View.GONE);
                    Toast.makeText(this,"Failed to connect to Tmdb Service",
                            Toast.LENGTH_LONG);
                    break;
            }
        });
        mainViewModel.getMovies().observe(this, pagedList -> mAdapter.submitList(pagedList));
    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new MovieEntityAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

}
