package com.example.wizelineproject.domain.repository

import com.example.local.entities.Genre
import com.example.local.entities.Movie


interface RemoteDataSource {
    suspend fun getLatestMovie(): com.example.local.entities.Movie
    suspend fun getNowPLayingMovies(): List<com.example.local.entities.Movie>
    suspend fun getTopRatedMovies(): List<com.example.local.entities.Movie>
    suspend fun getMoviesGenreList(): List<com.example.local.entities.Genre>
}