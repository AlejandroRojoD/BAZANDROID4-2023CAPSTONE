package com.example.wizelineproject.data.repository

import com.example.wizelineproject.data.database.dao.MoviesDao
import com.example.wizelineproject.data.database.entities.LatestMovieEntity
import com.example.wizelineproject.data.database.entities.NowPlayingMoviesEntity
import com.example.wizelineproject.data.database.entities.TopRatedMoviesEntity
import com.example.wizelineproject.domain.entities.toDomain
import com.example.wizelineproject.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao
) : DatabaseRepository {
    override suspend fun insertLatestMovie(latestMovieEntity: LatestMovieEntity) {
        moviesDao.insertLatestMovie(latestMovieEntity)
    }

    override suspend fun getLatestMovieFromDb(): LatestMovieEntity =
        moviesDao.getLatestMovie()

    override suspend fun insertNowPlayingMovies(nowPlayingMoviesEntity: List<NowPlayingMoviesEntity>) {
        moviesDao.insertNowPlayingMovies(nowPlayingMoviesEntity)
    }

    override suspend fun getNowPlayingMoviesFromDb(): List<NowPlayingMoviesEntity> =
        moviesDao.getNowPlayingMovies()

    override suspend fun insertTopRatedMovies(topRatedMoviesEntity: List<TopRatedMoviesEntity>) {
        moviesDao.insertTopRatedMovies(topRatedMoviesEntity)
    }

    override suspend fun getTopRatedMoviesFromDb(): List<TopRatedMoviesEntity> =
        moviesDao.getTopRatedMovies()


}