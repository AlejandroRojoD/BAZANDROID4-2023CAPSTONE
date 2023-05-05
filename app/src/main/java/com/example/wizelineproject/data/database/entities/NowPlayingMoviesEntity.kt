package com.example.wizelineproject.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.data.mappers.IntegerListConverter
import com.example.wizelineproject.domain.entities.Movie

@Entity(tableName = Constants.DataBase.NOW_PLAYING_MOVIES_TABLE)
data class NowPlayingMoviesEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterUrl: String,
    @TypeConverters(IntegerListConverter::class)
    val genreIds: List<Int> = listOf(),
    val backdropUrl: String = posterUrl,
    val overview: String,
    val rating: Double = 0.0,
    val releaseDate: String = "",
    val runtimeMinutes: Int?
)

fun Movie.toNowPlaying() = NowPlayingMoviesEntity(
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