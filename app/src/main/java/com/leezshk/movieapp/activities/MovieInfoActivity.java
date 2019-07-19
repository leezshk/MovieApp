package com.leezshk.movieapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leezshk.movieapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leesa Shakya on 7/18/2019.
 * leezshk@gmail.com
 */
public class MovieInfoActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.image_view_movie) ImageView mImageView;
    @BindView(R.id.text_view_movie_name) TextView mMovieNameTextView;
    @BindView(R.id.text_view_movie_description) TextView mOverviewTextView;
    @BindView(R.id.text_view_release_date) TextView mReleaseDateTextView;

    String title, posterPath, description, rating, releaseDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null){
            title = intent.getStringExtra("TITLE");
            posterPath = intent.getStringExtra("IMAGE_PATH");
            description = intent.getStringExtra("DESCRIPTION");
            rating = intent.getStringExtra("RATING");
            releaseDate = intent.getStringExtra("RELEASE_DATE");
        }

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(getDrawable(R.drawable.ic_keyboard_arrow_left));
        }

        mMovieNameTextView.setText(title);
        mOverviewTextView.setText(description);
        mReleaseDateTextView.setText(releaseDate);

        String path = "http://image.tmdb.org/t/p/w185" + posterPath;
        Glide.with(this)
                .asBitmap()
                .load(Uri.parse(path))
                .into(mImageView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId())
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
