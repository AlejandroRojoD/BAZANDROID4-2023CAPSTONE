package com.example.wizelineproject.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wizelineproject.config.Constants.DataBase.GENRE_MOVIES_TABLE
import com.example.wizelineproject.domain.entities.Genre

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