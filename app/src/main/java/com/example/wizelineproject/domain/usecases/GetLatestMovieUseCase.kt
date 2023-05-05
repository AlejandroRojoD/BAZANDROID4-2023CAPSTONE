package com.example.wizelineproject.domain.usecases

import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.repository.Repository
import javax.inject.Inject

class GetLatestMovieUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Result<Movie> = runCatching {
        return try {
            val movie = repository.getLatestMovie()
            Result.success(movie)
        } catch (e: Exception) {
            Result.failure(Error("Error al obtener la pelicula", e))
        }
    }
}