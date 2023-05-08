package com.example.wizelineproject.data.mappers

import com.example.local.entities.Movie
import com.example.wizelineproject.data.remote.model.MovieListResponse


fun MovieListResponse.toMovieList(): List<com.example.local.entities.Movie> =
    results.map { it.toMovie() }