package com.example.wizelineproject.domain.repository

import com.example.local.entities.Movie
import com.example.local.entities.MoviesWithGenres

interface Repository {

    suspend fun getLatestMovie(): com.example.local.entities.Movie

    suspend fun getNowPLayingMovies(): List<com.example.local.entities.Movie>

    suspend fun getTopRatedMovies(): List<com.example.local.entities.Movie>

    suspend fun getMovieWithGenre(movieId: Int): com.example.local.entities.MoviesWithGenres
}