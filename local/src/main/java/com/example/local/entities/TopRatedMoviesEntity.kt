package com.example.local.entities

import androidx.room.*
import com.example.local.Constants.DataBase.TOP_RATED_MOVIES_TABLE

@Entity(tableName = TOP_RATED_MOVIES_TABLE)
data class TopRatedMoviesEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterUrl: String,

    @ColumnInfo
    @TypeConverters(IntegerListConverter::class)
    val genreIds: List<Int>,
    val backdropUrl: String = posterUrl,
    val overview: String,
    val rating: Double = 0.0,
    val releaseDate: String = "",
    val runtimeMinutes: Int?
)

fun Movie.toTopRated() = TopRatedMoviesEntity(
    id,
    title,
    posterUrl,
    genreIds,
    backdropUrl,
    overview,
    rating,
    releaseDate,
    runtimeMinutes
)