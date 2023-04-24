package com.example.wizelineproject.data.repository

import com.example.wizelineproject.data.mappers.toMovie
import com.example.wizelineproject.data.mappers.toMovieList
import com.example.wizelineproject.data.network.MoviesApi
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) :
    MoviesRepository {

    override suspend fun getLatestMovie(): Movie =
        withContext(Dispatchers.IO) {
            moviesApi.getLatestMovies().toMovie()
        }

    override suspend fun getNowPLayingMovies(): List<Movie> =
        withContext(Dispatchers.IO) {
            moviesApi.getNowPlayingMovies().toMovieList()
        }

    override suspend fun getTopRatedMovies(): List<Movie> =
        withContext(Dispatchers.IO) {
            moviesApi.getTopRatedMovies().toMovieList()
        }
}