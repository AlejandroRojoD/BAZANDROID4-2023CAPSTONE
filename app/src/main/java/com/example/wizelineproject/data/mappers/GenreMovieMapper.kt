package com.example.wizelineproject.data.mappers

import com.example.wizelineproject.data.model.GenreResponse
import com.example.wizelineproject.domain.entities.Genre

fun GenreResponse.toGenre() = Genre(
    id = id,
    name = name
)