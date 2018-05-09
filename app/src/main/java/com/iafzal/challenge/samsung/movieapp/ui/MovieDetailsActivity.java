package com.iafzal.challenge.samsung.movieapp.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.iafzal.challenge.samsung.movieapp.R;
import com.iafzal.challenge.samsung.movieapp.api.TmdbWebService;
import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;
import com.iafzal.challenge.samsung.movieapp.utils.DateUtil;
import com.iafzal.challenge.samsung.movieapp.utils.GenreUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    MovieEntity movie;

    TextView tvDescription;

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        retrieveExtras();
        tvDescription = findViewById(R.id.tv_description);

        mImageView = findViewById(R.id.iv_poster);


        String lPosterPath = movie.getPoster_path();
        TmdbWebService.getInstance().fetchImage("w500",lPosterPath).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // display the image data in a ImageView or save it
                        Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                        mImageView.setImageBitmap(bm);
                    } else {
                        // TODO
                    }
                } else {
                    // TODO
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.getCause();
            }
        });

        tvDescription.setText("\nDescription:\n\n"+movie.getOverview());
        ((TextView)findViewById(R.id.tv_title)).setText(movie.getTitle());
        ((TextView)findViewById(R.id.tv_genre)).setText(GenreUtil.genreNamesFromIdList(movie.getGenre_ids()));
        ((TextView)findViewById(R.id.tv_release_date)).setText("Release Date:\n"+DateUtil.getInstance().formatShortDate(movie.getRelease_date()));
        ((TextView)findViewById(R.id.tv_adult)).setText("Mature content: "+ ((movie.getAdult() == true)?"Yes":"No"));
        ((TextView)findViewById(R.id.tv_popularity)).setText("Popularity:\n"+String.valueOf(movie.getPopularity()));
        ((TextView)findViewById(R.id.tv_vote_count)).setText("Vote count: "+String.valueOf(movie.getVote_count()));
        ((TextView)findViewById(R.id.tv_vote_average)).setText("Vote avg: "+String.valueOf(movie.getVote_average()) );
        ((TextView)findViewById(R.id.tv_original_language)).setText("Language: "+movie.getOriginal_language());
        ((TextView)findViewById(R.id.tv_original_title)).setText(movie.getOriginal_title());
    }


    private void retrieveExtras() {
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            if (extras.containsKey("com.iafzal.challenge.samsung.movieapp.movie")) {
                this.movie = (MovieEntity)extras.getSerializable("com.iafzal.challenge.samsung.movieapp.movie");
            }
        }
    }

    public static Intent newIntent(Context packageContext, MovieEntity movie){
        Intent i = new Intent(packageContext, MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.iafzal.challenge.samsung.movieapp.movie", movie);
        i.putExtras(bundle);
        return i;
    }




}
