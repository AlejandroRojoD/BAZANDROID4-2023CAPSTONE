package com.example.wizelineproject.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class MoviesWithGenres(
    @Embedded val topRatedMoviesEntity: TopRatedMoviesEntity,
    @Relation(
        parentColumn = "genreIds",
        entityColumn = "genreId"
    )
    val genres: List<MoviesGenreEntity>
)

