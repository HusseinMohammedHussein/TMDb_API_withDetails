package com.moviesapptmdbapi.Reset;


import com.moviesapptmdbapi.Model.Genre;

import java.util.List;

public interface OnGetGenresCallback {
    void onSuccess(List<Genre> movies);

    void onError();
}
