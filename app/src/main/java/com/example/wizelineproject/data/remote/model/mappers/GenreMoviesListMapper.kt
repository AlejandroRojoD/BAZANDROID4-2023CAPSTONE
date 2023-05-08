package com.example.wizelineproject.data.remote.model.mappers

import com.example.local.entities.Genre
import com.example.wizelineproject.data.remote.model.GenreListResponse


fun GenreListResponse.toGenreList(): List<Genre> =
    genres.map { it.toGenre() }