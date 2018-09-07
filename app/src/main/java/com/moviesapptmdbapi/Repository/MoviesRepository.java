package com.moviesapptmdbapi.Repository;

import com.moviesapptmdbapi.Model.GenresResponse;
import com.moviesapptmdbapi.Model.Movie;
import com.moviesapptmdbapi.Model.MoviesResponse;
import com.moviesapptmdbapi.Model.ReviewResponse;
import com.moviesapptmdbapi.Model.TrailerResponse;
import com.moviesapptmdbapi.Reset.OnGetGenresCallback;
import com.moviesapptmdbapi.Reset.OnGetMovieCallback;
import com.moviesapptmdbapi.Reset.OnGetMoviesCallback;
import com.moviesapptmdbapi.Reset.OnGetReviewsCallback;
import com.moviesapptmdbapi.Reset.OnGetTrailersCallback;
import com.moviesapptmdbapi.Reset.TMDbApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    //    Static fields
    public static final String POPULAR = "popular";
    public static final String TOP_RATE = "top_rate";
    public static final String UPCOMING = "upcoming";
    public static final String TMDB_API_KEY = "112d2d99cb1a29bdafde742aeb373107";

    //URL for fetch Movies
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    //Display Language
    private static final String LANGUAGE = "en-US";
    //Rest/MoviesRepository
    private static MoviesRepository repository;
    //Rest/TmdbApi
    private TMDbApi api;
    private MoviesRepository(TMDbApi api) {
        this.api = api;
    }

    public static MoviesRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            repository = new MoviesRepository(retrofit.create(TMDbApi.class));
        }
        return repository;
    }

    public void getMovies(int page, String sortBy, final OnGetMoviesCallback callback) {
        Callback<MoviesResponse> call = new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    MoviesResponse moviesResponse = response.body();
                    if (moviesResponse != null && moviesResponse.getMovies() != null) {
                        callback.onSuccess(moviesResponse.getPage(), moviesResponse.getMovies());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                callback.onError();
            }
        };

        switch (sortBy) {
            case TOP_RATE:
                api.getTopRateMovies(TMDB_API_KEY, LANGUAGE, page).enqueue(call);
                break;
            case POPULAR:
                api.getPopularMovies(TMDB_API_KEY, LANGUAGE, page).enqueue(call);
                break;
            case UPCOMING:
                api.getUpcomingMovies(TMDB_API_KEY, LANGUAGE, page).enqueue(call);
                break;
        }
    }


    public void getMovie(int movieId, final OnGetMovieCallback callback) {
        api.getMovie(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();
                    if (movie != null) {
                        callback.onSuccess(movie);
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onError();
            }
        });

    }


    public void getGenres(final OnGetGenresCallback callback) {
        api.getGenres(TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if (response.isSuccessful()) {
                            GenresResponse genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                callback.onSuccess(genresResponse.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    public void getTrailers(int movieId, final OnGetTrailersCallback callback) {
        api.getTrailers(movieId, TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            TrailerResponse trailerResponse = response.body();
                            if (trailerResponse != null && trailerResponse.getTrailers() != null) {
                                callback.onSuccess(trailerResponse.getTrailers());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getReviews(int mveId, final OnGetReviewsCallback callback) {
        api.getReviews(mveId, TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<ReviewResponse>() {
                    @Override
                    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                        if (response.isSuccessful()) {
                            ReviewResponse reviewResponse = response.body();
                            if (reviewResponse != null && reviewResponse.getReviews() != null) {
                                callback.onSuccess(reviewResponse.getReviews());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}


