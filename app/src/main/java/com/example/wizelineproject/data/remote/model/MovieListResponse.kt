package com.example.wizelineproject.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    var page: Int = 0,
    var results: List<MovieResponse> = listOf(),
    @SerializedName("total_pages") var totalPages: Int = 0,
    @SerializedName("total_results") var totalResults: Int = 0
)
