package com.example.wizelineproject.domain.usecases

import com.example.wizelineproject.data.database.entities.MoviesWithGenres
import com.example.wizelineproject.domain.repository.Repository
import javax.inject.Inject

class GetMovieWithGenresUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getMovieWithGenres(movieId: Int): Result<MoviesWithGenres> = runCatching {
        return try {
            val movies = repository.getMovieWithGenre(movieId)
            Result.success(movies)
        } catch (e: Exception) {
            Result.failure(Error("Error al obtener las peliculas", e))
        }
    }
}