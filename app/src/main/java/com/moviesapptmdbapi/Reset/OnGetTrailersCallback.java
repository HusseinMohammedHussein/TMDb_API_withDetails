package com.moviesapptmdbapi.Reset;

import com.moviesapptmdbapi.Model.Trailer;

import java.util.List;

public interface OnGetTrailersCallback {
    void onSuccess(List<Trailer> trailers);

    void onError();
}
