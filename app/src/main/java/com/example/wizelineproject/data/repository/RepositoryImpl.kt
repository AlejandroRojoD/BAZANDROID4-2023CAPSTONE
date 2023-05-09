package com.example.wizelineproject.data.repository

import com.example.local.entities.*
import com.example.local.repository.LocalDataSource
import com.example.wizelineproject.data.remote.repository.RemoteDataSource
import com.example.wizelineproject.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getNowPLayingMovies(): List<Movie> {
        val genresList: List<Genre>
        if (localDataSource.getMoviesGenreListFromDb().isEmpty()) {
            genresList = remoteDataSource.getMoviesGenreList()
            localDataSource.insertMoviesGenreList(genresList.map { it.toGenre() })
        }

        val data: List<Movie>
        if (localDataSource.getNowPlayingMoviesFromDb().isEmpty()
        ) {
            data = remoteDataSource.getNowPLayingMovies()
            localDataSource.insertNowPlayingMovies(data.map { it.toNowPlaying() })
        } else {
            data = localDataSource.getNowPlayingMoviesFromDb().map { it.toDomain() }
        }
        return data
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        val data: List<Movie>
        if (localDataSource.getTopRatedMoviesFromDb().isEmpty()) {
            data = remoteDataSource.getTopRatedMovies()
            localDataSource.insertTopRatedMovies(data.map { it.toTopRated() })
        } else {
            data = localDataSource.getTopRatedMoviesFromDb().map { it.toDomain() }
        }
        return data
    }

    override suspend fun getLatestMovie(): Movie {
        return remoteDataSource.getLatestMovie()
    }

    override suspend fun getMovieWithGenre(movieId: Int): MoviesWithGenres =
        localDataSource.getGenresForMovie(movieId)

}