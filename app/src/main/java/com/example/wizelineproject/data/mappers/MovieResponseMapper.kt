package com.example.wizelineproject.data.mappers

import com.example.wizelineproject.config.Constants.IMAGE_PREFIX
import com.example.local.entities.Movie
import com.example.wizelineproject.data.remote.model.MovieResponse


fun MovieResponse.toMovie() = com.example.local.entities.Movie(
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