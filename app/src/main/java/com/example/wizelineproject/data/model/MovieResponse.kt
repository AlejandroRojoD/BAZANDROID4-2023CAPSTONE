package com.example.wizelineproject.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val adult: Boolean = false,
    @SerializedName("backdrop_path") var backdropPath: String = "",
    @SerializedName("genre_ids") var genreIds: List<Int> = listOf(),
    var id: Int = 0,
    @SerializedName("original_language") var originalLanguage: String = "",
    @SerializedName("original_title") var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double? = 0.0,
    @SerializedName("poster_path") var posterPath: String = "",
    @SerializedName("release_date") var releaseDate: String = "",
    var title: String = "",
    var video: Boolean = false,
    @SerializedName("vote_average") var voteAverage: Double = 0.0,
    @SerializedName("vote_count") var voteCount: Int = 0
)
