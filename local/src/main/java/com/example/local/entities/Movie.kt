package com.example.local.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String = "",
    val posterUrl: String = "",
    val backdropUrl: String = posterUrl,
    val overview: String = "",
    val genreIds: List<Int> = listOf(),
    val rating: Double = 0.0,
    val releaseDate: String = "",
    val runtimeMinutes: Int?
) : Parcelable

fun LatestMovieEntity.toDomain() = Movie(
    id,
    title,
    posterUrl,
    backdropUrl,
    overview,
    genreIds,
    rating,
    releaseDate,
    runtimeMinutes,
)

fun NowPlayingMoviesEntity.toDomain() = Movie(
    id,
    title,
    posterUrl,
    backdropUrl,
    overview,
    genreIds,
    rating,
    releaseDate,
    runtimeMinutes,
)

fun TopRatedMoviesEntity.toDomain() = Movie(
    id,
    title,
    posterUrl,
    backdropUrl,
    overview,
    genreIds,
    rating,
    releaseDate,
    runtimeMinutes,
)