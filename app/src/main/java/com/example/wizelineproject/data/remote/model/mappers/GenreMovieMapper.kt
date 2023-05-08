package com.example.wizelineproject.data.remote.model.mappers

import com.example.local.entities.Genre
import com.example.wizelineproject.data.remote.model.GenreResponse

fun GenreResponse.toGenre() = Genre(
    id = id,
    name = name
)