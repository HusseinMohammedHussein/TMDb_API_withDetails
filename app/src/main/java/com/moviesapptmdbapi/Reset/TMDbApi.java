package com.moviesapptmdbapi.Reset;

import com.moviesapptmdbapi.Model.GenresResponse;
import com.moviesapptmdbapi.Model.Movie;
import com.moviesapptmdbapi.Model.MoviesResponse;
import com.moviesapptmdbapi.Model.ReviewResponse;
import com.moviesapptmdbapi.Model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDbApi {

//    GET Movies Popular
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
//  GET Movie Top Rated
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRateMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
//  GET Movie Upcoming
    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
//  GET Genre Movie List
    @GET("genre/movie/list")
    Call<GenresResponse> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
//  GET a Movie by ID
    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
//  GET Videos of a Movie
    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailers(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
//  GET Reviews of a Movie
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getReviews(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}

