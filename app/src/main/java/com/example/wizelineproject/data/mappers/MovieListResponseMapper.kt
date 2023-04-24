package com.example.wizelineproject.data.mappers

import com.example.wizelineproject.data.model.MovieListResponse
import com.example.wizelineproject.domain.entities.Movie

fun MovieListResponse.toMovieList(): List<Movie> =
    results.map { it.toMovie() }