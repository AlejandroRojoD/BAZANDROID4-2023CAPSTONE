package com.example.wizelineproject.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.domain.entities.Movie

@Entity(tableName = Constants.DataBase.LATEST_MOVIE_TABLE)
data class LatestMovieEntity(
    @PrimaryKey
    val id: String,
    val title: String = "",
    val posterUrl: String = "",
    val backdropUrl: String = posterUrl,
    val overview: String = "",
    val rating: Double = 0.0,
    val releaseDate: String = "",
    val runtimeMinutes: Int?
)

fun Movie.toLatest() = LatestMovieEntity(
    id,
    title,
    posterUrl,
    backdropUrl,
    overview,
    rating,
    releaseDate,
    runtimeMinutes
)
