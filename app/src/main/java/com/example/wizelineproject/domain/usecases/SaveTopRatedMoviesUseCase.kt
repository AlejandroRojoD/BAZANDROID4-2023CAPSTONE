package com.example.wizelineproject.domain.usecases

import com.example.wizelineproject.data.database.entities.toNowPlaying
import com.example.wizelineproject.data.database.entities.toTopRated
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.repository.DatabaseRepository
import javax.inject.Inject

class SaveTopRatedMoviesUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(movies: List<Movie>?) {
        if (movies != null) {
            databaseRepository.insertTopRatedMovies(movies.map { it.toTopRated() })
        }
    }
}
