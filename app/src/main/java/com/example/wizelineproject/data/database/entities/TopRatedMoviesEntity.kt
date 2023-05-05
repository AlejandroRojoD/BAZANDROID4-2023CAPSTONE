package com.example.wizelineproject.data.database.entities

import androidx.room.*
import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.data.mappers.IntegerListConverter
import com.example.wizelineproject.domain.entities.Movie

@Entity(tableName = Constants.DataBase.TOP_RATED_MOVIES_TABLE)
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