package com.example.wizelineproject.data.remote.model.mappers

import com.example.local.entities.Movie
import com.example.wizelineproject.data.remote.model.MovieListResponse


fun MovieListResponse.toMovieList(): List<Movie> =
    results.map { it.toMovie() }