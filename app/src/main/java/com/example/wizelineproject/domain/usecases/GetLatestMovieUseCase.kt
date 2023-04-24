package com.example.wizelineproject.domain.usecases

import android.util.Log
import com.example.wizelineproject.data.database.entities.toLatest
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.entities.toDomain
import com.example.wizelineproject.domain.repository.DatabaseRepository
import com.example.wizelineproject.domain.repository.MoviesRepository
import javax.inject.Inject

class GetLatestMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(): Result<Movie> = runCatching {
        getLatestMovie()
    }

    private suspend fun getLatestMovie(): Movie {
        val data = moviesRepository.getLatestMovie()
            databaseRepository.insertLatestMovie(data.toLatest())

        return data

/*        val data: Movie
        Log.e("GetLatestMovieUseCase", "paso NADA")
        val temp = databaseRepository.getLatestMovieFromDb()
        Log.e("GetLatestMovieUseCase", "paso ${temp.title}")
        if (temp.title.isEmpty() &&
            temp.overview.isEmpty() &&
            temp.posterUrl.isEmpty()
        ) {
            data = moviesRepository.getLatestMovie()
            databaseRepository.insertLatestMovie(data.toLatest())
        } else {
            data = databaseRepository.getLatestMovieFromDb().toDomain()
        }

        return data*/
    }
}