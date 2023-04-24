package com.example.wizelineproject.domain.usecases

import com.example.wizelineproject.data.database.entities.toLatest
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.repository.DatabaseRepository
import javax.inject.Inject

class SaveLatestMovieUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(movie: Movie?) {
        if (movie != null) {
            databaseRepository.insertLatestMovie(movie.toLatest())
        }
    }
}