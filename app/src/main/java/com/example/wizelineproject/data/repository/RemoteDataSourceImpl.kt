package com.example.wizelineproject.data.repository

import com.example.local.entities.Genre
import com.example.local.entities.Movie
import com.example.wizelineproject.data.mappers.toGenreList
import com.example.wizelineproject.data.mappers.toMovie
import com.example.wizelineproject.data.mappers.toMovieList
import com.example.wizelineproject.data.remote.service.MoviesApi
import com.example.wizelineproject.domain.repository.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) :
    RemoteDataSource {

    override suspend fun getLatestMovie(): com.example.local.entities.Movie =
        withContext(Dispatchers.IO) {
            moviesApi.getLatestMovies().toMovie()
        }

    override suspend fun getNowPLayingMovies(): List<com.example.local.entities.Movie> =
        withContext(Dispatchers.IO) {
            moviesApi.getNowPlayingMovies().toMovieList()
        }

    override suspend fun getTopRatedMovies(): List<com.example.local.entities.Movie> =
        withContext(Dispatchers.IO) {
            moviesApi.getTopRatedMovies().toMovieList()
        }

    override suspend fun getMoviesGenreList(): List<com.example.local.entities.Genre> =
        withContext(Dispatchers.IO) {
            moviesApi.getMoviesGenreList().toGenreList()
        }

}