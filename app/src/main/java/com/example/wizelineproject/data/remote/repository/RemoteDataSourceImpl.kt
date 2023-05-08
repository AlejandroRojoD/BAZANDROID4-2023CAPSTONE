package com.example.wizelineproject.data.remote.repository

import com.example.local.entities.Genre
import com.example.local.entities.Movie
import com.example.wizelineproject.data.remote.model.mappers.toGenreList
import com.example.wizelineproject.data.remote.model.mappers.toMovie
import com.example.wizelineproject.data.remote.model.mappers.toMovieList
import com.example.wizelineproject.data.remote.service.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) :
    RemoteDataSource {

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

    override suspend fun getMoviesGenreList(): List<Genre> =
        withContext(Dispatchers.IO) {
            moviesApi.getMoviesGenreList().toGenreList()
        }

}