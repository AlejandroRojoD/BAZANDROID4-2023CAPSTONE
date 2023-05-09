package com.example.wizelineproject.data.remote.model.mappers

import com.example.local.entities.Movie
import com.example.wizelineproject.data.remote.Constants.IMAGE_PREFIX
import com.example.wizelineproject.data.remote.model.MovieResponse


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