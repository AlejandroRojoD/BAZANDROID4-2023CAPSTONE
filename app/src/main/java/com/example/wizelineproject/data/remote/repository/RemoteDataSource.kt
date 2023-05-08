package com.example.wizelineproject.data.remote.repository

import com.example.local.entities.Genre
import com.example.local.entities.Movie


interface RemoteDataSource {
    suspend fun getLatestMovie(): Movie
    suspend fun getNowPLayingMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
    suspend fun getMoviesGenreList(): List<Genre>
}