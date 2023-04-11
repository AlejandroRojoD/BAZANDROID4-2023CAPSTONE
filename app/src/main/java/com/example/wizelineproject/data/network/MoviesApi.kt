package com.example.wizelineproject.data.network

import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.data.model.MovieListResponse
import com.example.wizelineproject.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET(Constants.WebServices.GET_LATEST)
    suspend fun getLatestMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET(Constants.WebServices.GET_NOW_PLAYING)
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): MovieListResponse

    @GET(Constants.WebServices.GET_TOP_RATED)
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieListResponse
}