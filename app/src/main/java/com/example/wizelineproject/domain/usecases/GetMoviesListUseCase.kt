package com.example.wizelineproject.domain.usecases

import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.repository.Repository
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Result<List<Movie>> = runCatching {
        return try {
            val movies = repository.getNowPLayingMovies()
            Result.success(movies)
        } catch (e: Exception) {
            Result.failure(Error("Error al obtener las peliculas", e))
        }
    }
}