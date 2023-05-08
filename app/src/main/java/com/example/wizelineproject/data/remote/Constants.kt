package com.example.wizelineproject.data.remote

object Constants {
    const val URL_BASE = "https://api.themoviedb.org/3/"
    const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/original"

    object WebServices {
        const val GET_LATEST = "movie/latest"
        const val GET_NOW_PLAYING = "movie/now_playing"
        const val GET_TOP_RATED = "movie/top_rated"
        const val GET_MOVIES_GENRE = "genre/movie/list"
    }
}