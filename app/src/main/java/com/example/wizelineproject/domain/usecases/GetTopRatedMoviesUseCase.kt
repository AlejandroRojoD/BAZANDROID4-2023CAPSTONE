package com.example.wizelineproject.domain.usecases

import com.example.wizelineproject.data.database.entities.toTopRated
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.entities.toDomain
import com.example.wizelineproject.domain.repository.DatabaseRepository
import com.example.wizelineproject.domain.repository.MoviesRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(): Result<List<Movie>> = runCatching {
        getTopRatedMovies()
    }

    private suspend fun getTopRatedMovies(): List<Movie> {
        val data: List<Movie>
        if (databaseRepository.getTopRatedMoviesFromDb().isNullOrEmpty()) {
            data = moviesRepository.getTopRatedMovies()
            databaseRepository.insertTopRatedMovies(data.map { it.toTopRated() })
        } else {
            data = databaseRepository.getTopRatedMoviesFromDb().map { it.toDomain() }
        }
        return data
    }
}