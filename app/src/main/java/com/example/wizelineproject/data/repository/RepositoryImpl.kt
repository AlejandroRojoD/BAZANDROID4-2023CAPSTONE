package com.example.wizelineproject.data.repository

import com.example.local.entities.toDomain
import com.example.local.entities.toGenre
import com.example.local.entities.toNowPlaying
import com.example.local.entities.toTopRated
import com.example.wizelineproject.domain.repository.RemoteDataSource
import com.example.wizelineproject.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: com.example.local.repository.LocalDataSource
) : Repository {

    override suspend fun getNowPLayingMovies(): List<com.example.local.entities.Movie> {
        val genresList: List<com.example.local.entities.Genre>
        if (localDataSource.getMoviesGenreListFromDb().isEmpty()) {
            genresList = remoteDataSource.getMoviesGenreList()
            localDataSource.insertMoviesGenreList(genresList.map { it.toGenre() })
        }

        val data: List<com.example.local.entities.Movie>
        if (localDataSource.getNowPlayingMoviesFromDb().isEmpty()
        ) {
            data = remoteDataSource.getNowPLayingMovies()
            localDataSource.insertNowPlayingMovies(data.map { it.toNowPlaying() })
        } else {
            data = localDataSource.getNowPlayingMoviesFromDb().map { it.toDomain() }
        }
        return data
    }

    override suspend fun getTopRatedMovies(): List<com.example.local.entities.Movie> {
        val data: List<com.example.local.entities.Movie>
        if (localDataSource.getTopRatedMoviesFromDb().isEmpty()) {
            data = remoteDataSource.getTopRatedMovies()
            localDataSource.insertTopRatedMovies(data.map { it.toTopRated() })
        } else {
            data = localDataSource.getTopRatedMoviesFromDb().map { it.toDomain() }
        }
        return data
    }

    override suspend fun getLatestMovie(): com.example.local.entities.Movie {
        return remoteDataSource.getLatestMovie()
    }

    override suspend fun getMovieWithGenre(movieId: Int): com.example.local.entities.MoviesWithGenres =
        localDataSource.getGenresForMovie(movieId)

}