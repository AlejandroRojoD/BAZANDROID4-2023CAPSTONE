package com.example.wizelineproject.domain.usecases

import com.example.wizelineproject.data.database.entities.toNowPlaying
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.entities.toDomain
import com.example.wizelineproject.domain.repository.DatabaseRepository
import com.example.wizelineproject.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(): Result<List<Movie>> = runCatching {
        getMovies()
    }

    private suspend fun getMovies(): List<Movie> {
        val data: List<Movie>
        if (databaseRepository.getNowPlayingMoviesFromDb().isNullOrEmpty()
        ) {
            data = moviesRepository.getNowPLayingMovies()
            databaseRepository.insertNowPlayingMovies(data.map { it.toNowPlaying() })
        } else {
            data = databaseRepository.getNowPlayingMoviesFromDb().map { it.toDomain() }
        }
        return data
    }
}