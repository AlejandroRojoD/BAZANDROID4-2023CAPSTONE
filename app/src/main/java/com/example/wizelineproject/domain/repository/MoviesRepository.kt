package com.example.wizelineproject.domain.repository

import com.example.wizelineproject.domain.entities.Movie

interface MoviesRepository {
    suspend fun getLatestMovie(): Movie

    suspend fun getNowPLayingMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>
}