package com.example.wizelineproject.config

object Constants {
    const val URL_BASE = "https://api.themoviedb.org/3/"
    const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/original"
    const val API_KEY = "9d9838eb01624dc31d119f2a25e345ba"

    object WebServices {
        const val GET_LATEST = "movie/latest"
        const val GET_NOW_PLAYING = "movie/now_playing"
        const val GET_TOP_RATED = "movie/top_rated"
    }
}