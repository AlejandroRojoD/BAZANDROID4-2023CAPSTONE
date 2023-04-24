package com.example.wizelineproject.config

import com.example.wizelineproject.domain.entities.Movie

data class DataState(
    val isLoading: Boolean = false,
    val moviesList: List<Movie> = emptyList(),
    val latestMovie: Movie? = null,
    val errorMessage: String? = null,
)