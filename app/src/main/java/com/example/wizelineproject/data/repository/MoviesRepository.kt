package com.example.wizelineproject.data.repository

import com.example.wizelineproject.data.model.MovieListResponse
import com.example.wizelineproject.data.model.MovieResponse

interface MoviesRepository {
    suspend fun getLatestMovie(): MovieResponse

    suspend fun getNowPLayingMovies(): MovieListResponse

    suspend fun getTopRatedMovies(): MovieListResponse
}