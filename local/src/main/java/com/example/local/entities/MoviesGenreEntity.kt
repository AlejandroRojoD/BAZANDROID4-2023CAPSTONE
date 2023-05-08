package com.example.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.local.Constants.DataBase.GENRE_MOVIES_TABLE

@Entity(tableName = GENRE_MOVIES_TABLE)
data class MoviesGenreEntity(
    @PrimaryKey
    val genreId: Int = 0,
    val name: String
)

fun Genre.toGenre() = MoviesGenreEntity(
    genreId = id,
    name = name
)