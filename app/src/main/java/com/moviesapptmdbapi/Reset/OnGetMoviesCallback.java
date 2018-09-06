package com.moviesapptmdbapi.Reset;

import com.moviesapptmdbapi.Model.Movie;

import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(int page, List<Movie> movies);

    void onError();
}
