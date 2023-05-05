package com.example.wizelineproject.domain.entities

import android.os.Parcelable
import com.example.wizelineproject.data.database.entities.MoviesGenreEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val id: Int,
    val name: String
) : Parcelable

fun MoviesGenreEntity.toDomain() = Genre(
    id = genreId,
    name = name
)