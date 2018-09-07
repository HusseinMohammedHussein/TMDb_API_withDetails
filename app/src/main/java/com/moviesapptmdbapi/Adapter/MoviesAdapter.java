package com.moviesapptmdbapi.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.moviesapptmdbapi.Model.Genre;
import com.moviesapptmdbapi.Model.Movie;
import com.moviesapptmdbapi.R;
import com.moviesapptmdbapi.Reset.OnMoviesClickCallback;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies;
    private List<Genre> allGenres;
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private OnMoviesClickCallback callback;
    public MoviesAdapter(List<Movie> mvis, List<Genre> allGenres, OnMoviesClickCallback callback) {
        this.mMovies = mvis;
        this.allGenres = allGenres;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView releaseDate, title, rating, genres;
        ImageView poster;
        Movie movie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            releaseDate = itemView.findViewById(R.id.item_movie_release_date);
            title = itemView.findViewById(R.id.item_movie_title);
            rating = itemView.findViewById(R.id.item_movie_rating);
            genres = itemView.findViewById(R.id.item_movie_genre);
            poster = itemView.findViewById(R.id.item_movie_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(movie);
                }
            });
        }

        public void bind(Movie movie) {
            releaseDate.setText(movie.getReleaseDate().split("-")[0]);
            title.setText(movie.getTitle());
            rating.setText(String.valueOf(movie.getRating()));
            genres.setText(getGenres(movie.getGenreIds()));
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.mipmap.ic_app_icon))
                    .into(poster);
            this.movie = movie;
        }

        private String getGenres(List<Integer> genreIds) {
            List<String> movieGenres = new ArrayList<>();
            for (Integer genreId : genreIds) {
                for (Genre genre : allGenres) {
                    if (genre.getId() == genreId) {
                        movieGenres.add(genre.getName());
                        break;
                    }
                }
            }
            return TextUtils.join(",  ", movieGenres);
        }
    }

    public void appendMovies(List<Movie> moviesToAppend) {
        mMovies.addAll(moviesToAppend);
        notifyDataSetChanged();
    }

    public void clearMovies() {
        mMovies.clear();
        notifyDataSetChanged();
    }
}
