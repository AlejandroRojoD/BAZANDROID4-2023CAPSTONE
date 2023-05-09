package com.example.wizelineproject.data.mappers

import com.example.wizelineproject.config.Constants.IMAGE_PREFIX
import com.example.wizelineproject.data.model.MovieResponse
import com.example.wizelineproject.domain.entities.Movie

fun MovieResponse.toMovie() = Movie(
    id = id,
    title = title.ifEmpty { "" },
    posterUrl = completeImageUrl(posterPath) ?: "",
    backdropUrl = completeImageUrl(backdropPath) ?: "",
    overview = overview,
    genreIds = genreIds,
    rating = voteAverage,
    releaseDate = releaseDate,
    runtimeMinutes = null
)

private fun completeImageUrl(relativePath: String?) =
    relativePath?.let { IMAGE_PREFIX + it }