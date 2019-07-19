package com.leezshk.movieapp.activities;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leezshk.movieapp.R;
import com.leezshk.movieapp.dto.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leesa Shakya on 7/17/2019.
 * leezshk@gmail.com
 */

public class MovieListAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<Movie> movieList;
    private onClickListener listener;

    MovieListAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_movie_list, viewGroup, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof MovieListViewHolder)) return;

        MovieListViewHolder movieListViewHolder = (MovieListViewHolder) holder;
        final Movie movie = movieList.get(position);
        if (movie == null) return;

        String title = movie.getTitle();
        String posterPath = movie.getPosterPath();
        String path = "http://image.tmdb.org/t/p/w185" + posterPath;

        movieListViewHolder.mMovieNameTextView.setText(title);

        Glide.with(context)
                .asBitmap()
                .load(Uri.parse(path))
                .into(movieListViewHolder.mImageView);

        movieListViewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClicked(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.relative_layout) RelativeLayout mLayout;
        @BindView(R.id.image_view_movie) ImageView mImageView;
        @BindView(R.id.text_view_movie_name) TextView mMovieNameTextView;

        MovieListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setOnClickListener(onClickListener onClickListener){
        this.listener = onClickListener;
    }

    public interface onClickListener{
        void onMovieClicked(Movie movie);
    }
}
