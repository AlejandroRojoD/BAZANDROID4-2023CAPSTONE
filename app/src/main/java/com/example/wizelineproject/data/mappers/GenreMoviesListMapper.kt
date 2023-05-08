package com.example.wizelineproject.data.mappers

import com.example.local.entities.Genre
import com.example.wizelineproject.data.remote.model.GenreListResponse


fun GenreListResponse.toGenreList(): List<com.example.local.entities.Genre> =
    genres.map { it.toGenre() }