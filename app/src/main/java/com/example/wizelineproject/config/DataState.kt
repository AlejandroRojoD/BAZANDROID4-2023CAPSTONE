package com.example.wizelineproject.config

import com.example.local.entities.Movie

data class DataState(
    val isLoading: Boolean = false,
    val moviesList: List<com.example.local.entities.Movie> = emptyList(),
    val latestMovie: com.example.local.entities.Movie? = null,
    val errorMessage: String? = null,
    val genreList: List<String> = emptyList()
)