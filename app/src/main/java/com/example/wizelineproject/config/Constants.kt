package com.example.wizelineproject.config

object Constants {
    const val URL_BASE = "https://api.themoviedb.org/3/"
    const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/original"

    object WebServices {
        const val GET_LATEST = "movie/latest"
        const val GET_NOW_PLAYING = "movie/now_playing"
        const val GET_TOP_RATED = "movie/top_rated"
        const val GET_MOVIES_GENRE = "genre/movie/list"
    }

    object DataBase {
        const val DATABASE_NAME = "movies"
        const val DATABASE_VERSION = 1
        const val NOW_PLAYING_MOVIES_TABLE = "now_playing_movies_table"
        const val TOP_RATED_MOVIES_TABLE = "top_rated_movies_table"
        const val LATEST_MOVIE_TABLE = "latest_movie_table"
        const val GENRE_MOVIES_TABLE = "genre_movies_table"
    }
}