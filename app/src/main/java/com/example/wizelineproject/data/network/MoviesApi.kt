package com.example.wizelineproject.data.network

import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.data.model.MovieListResponse
import com.example.wizelineproject.data.model.MovieResponse
import retrofit2.http.GET

interface MoviesApi {
    @GET(Constants.WebServices.GET_LATEST)
    suspend fun getLatestMovies(): MovieResponse

    @GET(Constants.WebServices.GET_NOW_PLAYING)
    suspend fun getNowPlayingMovies(): MovieListResponse

    @GET(Constants.WebServices.GET_TOP_RATED)
    suspend fun getTopRatedMovies(): MovieListResponse
}