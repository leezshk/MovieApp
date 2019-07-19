package com.leezshk.movieapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.leezshk.movieapp.R;
import com.leezshk.movieapp.api.Endpoints;
import com.leezshk.movieapp.dto.Movie;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.leezshk.movieapp.MovieApp.getMyApplication;

/**
 * Created by Leesa Shakya on 7/17/2019.
 * leezshk@gmail.com
 */
public class MovieListActivity extends AppCompatActivity implements MovieListView{
    @Inject Endpoints endpoints;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    MovieListAdapter adapter;
    MovieListPresenter presenter;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Movies");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(getDrawable(R.drawable.ic_drag_handle));
        }

        getMyApplication(this).getAppComponent().inject(this);
        presenter = new MovieListPresenterImpl(this, endpoints);
        presenter.getMovieList();
    }


    @Override
    public void getMovieListSuccess(List<Movie> movieList) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new MovieListAdapter(this, movieList);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new MovieListAdapter.onClickListener() {
            @Override
            public void onMovieClicked(Movie movie) {
                startActivity(new Intent(MovieListActivity.this ,MovieInfoActivity.class)
                        .putExtra("TITLE", movie.getTitle())
                        .putExtra("IMAGE_PATH", movie.getPosterPath())
                        .putExtra("DESCRIPTION", movie.getOverview())
                        .putExtra("RATING", movie.getRating())
                        .putExtra("RELEASE_DATE", movie.getReleaseDate())
                );
            }
        });
    }

    @Override
    public void getMovieListFailure() {
        Toast.makeText(this, "Sorry. Try Again.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
        if (mProgressDialog.getWindow() != null) {
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mProgressDialog.setContentView(R.layout.progress_dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
    }
}
