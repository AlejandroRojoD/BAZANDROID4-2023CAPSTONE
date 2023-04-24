package com.example.wizelineproject.domain.repository

import com.example.wizelineproject.data.database.entities.LatestMovieEntity
import com.example.wizelineproject.data.database.entities.NowPlayingMoviesEntity
import com.example.wizelineproject.data.database.entities.TopRatedMoviesEntity

interface DatabaseRepository {
    suspend fun insertLatestMovie(latestMovieEntity: LatestMovieEntity)
    suspend fun getLatestMovieFromDb(): LatestMovieEntity
    suspend fun insertNowPlayingMovies(nowPlayingMoviesEntity: List<NowPlayingMoviesEntity>)
    suspend fun getNowPlayingMoviesFromDb(): List<NowPlayingMoviesEntity>
    suspend fun insertTopRatedMovies(topRatedMoviesEntity: List<TopRatedMoviesEntity>)
    suspend fun getTopRatedMoviesFromDb(): List<TopRatedMoviesEntity>
}