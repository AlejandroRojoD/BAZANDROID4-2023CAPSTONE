package com.example.wizelineproject.domain.repository

import com.example.wizelineproject.data.database.entities.MoviesWithGenres
import com.example.wizelineproject.domain.entities.Movie

interface Repository {

    suspend fun getLatestMovie(): Movie

    suspend fun getNowPLayingMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getMovieWithGenre(movieId: Int): MoviesWithGenres
}