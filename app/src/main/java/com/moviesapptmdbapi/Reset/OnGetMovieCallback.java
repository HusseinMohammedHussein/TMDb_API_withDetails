package com.moviesapptmdbapi.Reset;

import com.moviesapptmdbapi.Model.Movie;

public interface OnGetMovieCallback {

    void onSuccess(Movie movie);

    void onError();
}
