package com.example.wizelineproject.data.mappers

import com.example.wizelineproject.data.model.GenreListResponse
import com.example.wizelineproject.domain.entities.Genre

fun GenreListResponse.toGenreList(): List<Genre> =
    genres.map { it.toGenre() }