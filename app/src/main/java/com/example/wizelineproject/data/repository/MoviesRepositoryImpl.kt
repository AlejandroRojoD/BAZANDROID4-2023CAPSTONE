package com.example.wizelineproject.data.repository

import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.data.model.MovieListResponse
import com.example.wizelineproject.data.model.MovieResponse
import com.example.wizelineproject.data.network.MoviesApi
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesApi: MoviesApi) :
    MoviesRepository {

    override suspend fun getLatestMovie(): MovieResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPLayingMovies(): MovieListResponse =
        moviesApi.getNowPlayingMovies(Constants.API_KEY)

    override suspend fun getTopRatedMovies(): MovieListResponse {
        TODO("Not yet implemented")
    }
}