package com.example.wizelineproject.domain.repository

import com.example.wizelineproject.data.database.entities.*

interface LocalDataSource {
    suspend fun insertLatestMovie(latestMovieEntity: LatestMovieEntity)
    suspend fun getLatestMovieFromDb(): LatestMovieEntity
    suspend fun insertNowPlayingMovies(nowPlayingMoviesEntity: List<NowPlayingMoviesEntity>)
    suspend fun getNowPlayingMoviesFromDb(): List<NowPlayingMoviesEntity>
    suspend fun insertTopRatedMovies(topRatedMoviesEntity: List<TopRatedMoviesEntity>)
    suspend fun getTopRatedMoviesFromDb(): List<TopRatedMoviesEntity>
    suspend fun insertMoviesGenreList(moviesGenreEntity: List<MoviesGenreEntity>)
    suspend fun getMoviesGenreListFromDb(): List<MoviesGenreEntity>
    suspend fun getGenresForMovie(movieId: Int): MoviesWithGenres
}