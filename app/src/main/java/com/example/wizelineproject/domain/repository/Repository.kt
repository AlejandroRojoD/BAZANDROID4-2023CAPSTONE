package com.example.wizelineproject.domain.repository

import com.example.local.entities.Movie
import com.example.local.entities.MoviesWithGenres

interface Repository {

    suspend fun getLatestMovie(): Movie

    suspend fun getNowPLayingMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getMovieWithGenre(movieId: Int): MoviesWithGenres
}