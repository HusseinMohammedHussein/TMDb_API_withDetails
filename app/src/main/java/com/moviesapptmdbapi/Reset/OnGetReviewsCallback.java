package com.moviesapptmdbapi.Reset;

import com.moviesapptmdbapi.Model.Review;

import java.util.List;

public interface OnGetReviewsCallback {

    void onSuccess(List<Review> reviews);

    void onError();
}
