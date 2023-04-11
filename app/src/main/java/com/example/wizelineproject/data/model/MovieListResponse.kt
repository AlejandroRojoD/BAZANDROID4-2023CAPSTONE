package com.example.wizelineproject.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int = 0,
    val result: List<MovieResponse>,
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("total_results") val totalResults: Int = 0
)
