package com.example.wizelineproject.data.remote.service

import com.example.wizelineproject.data.remote.Constants
import com.example.wizelineproject.data.remote.model.GenreListResponse
import com.example.wizelineproject.data.remote.model.MovieListResponse
import com.example.wizelineproject.data.remote.model.MovieResponse
import retrofit2.http.GET

interface MoviesApi {
    @GET(Constants.WebServices.GET_LATEST)
    suspend fun getLatestMovies(): MovieResponse

    @GET(Constants.WebServices.GET_NOW_PLAYING)
    suspend fun getNowPlayingMovies(): MovieListResponse

    @GET(Constants.WebServices.GET_TOP_RATED)
    suspend fun getTopRatedMovies(): MovieListResponse

    @GET(Constants.WebServices.GET_MOVIES_GENRE)
    suspend fun getMoviesGenreList(): GenreListResponse
}