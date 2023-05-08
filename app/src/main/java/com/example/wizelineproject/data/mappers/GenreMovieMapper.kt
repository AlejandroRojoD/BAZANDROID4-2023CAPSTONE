package com.example.wizelineproject.data.mappers

import com.example.local.entities.Genre
import com.example.wizelineproject.data.remote.model.GenreResponse

fun GenreResponse.toGenre() = com.example.local.entities.Genre(
    id = id,
    name = name
)