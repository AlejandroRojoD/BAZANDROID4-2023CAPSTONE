package com.example.wizelineproject.domain.repository

import com.example.wizelineproject.domain.entities.Genre
import com.example.wizelineproject.domain.entities.Movie

interface RemoteDataSource {
    suspend fun getLatestMovie(): Movie
    suspend fun getNowPLayingMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
    suspend fun getMoviesGenreList(): List<Genre>
}