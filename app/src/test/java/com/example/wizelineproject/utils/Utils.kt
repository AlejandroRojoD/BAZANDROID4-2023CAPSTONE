package com.example.wizelineproject.utils

import com.example.local.entities.*


object Utils {
    fun createMovieLatestEntity(): LatestMovieEntity {
        return LatestMovieEntity(
            id = 1,
            title = "title",
            posterUrl = "posterUrl",
            genreIds = listOf(1, 3, 4, 6),
            backdropUrl = "backdrop",
            overview = "overview",
            rating = 7.7,
            releaseDate = "12/12/2002",
            runtimeMinutes = 140
        )
    }

    fun createNowPlayingMoviesEntity(): NowPlayingMoviesEntity {
        return NowPlayingMoviesEntity(
            id = 1,
            title = "title",
            posterUrl = "posterUrl",
            genreIds = listOf(1, 3, 4, 6),
            backdropUrl = "backdrop",
            overview = "overview",
            rating = 7.7,
            releaseDate = "12/12/2002",
            runtimeMinutes = 140
        )
    }

    fun createNowPlayingMoviesEntityList(): List<NowPlayingMoviesEntity> {
        return listOf(
            NowPlayingMoviesEntity(
                id = 1,
                title = "title1",
                posterUrl = "url",
                genreIds = listOf(1),
                backdropUrl = "backDrop",
                overview = "overview",
                rating = 6.7,
                releaseDate = "12/12/2023",
                runtimeMinutes = 200
            ),
            NowPlayingMoviesEntity(
                id = 2,
                title = "title2",
                posterUrl = "url",
                genreIds = listOf(2),
                backdropUrl = "backDrop",
                overview = "overview",
                rating = 6.7,
                releaseDate = "12/12/2023",
                runtimeMinutes = 200
            )
        )
    }

    fun createTopRatedMoviesEntityList(): List<TopRatedMoviesEntity> {
        return listOf(
            TopRatedMoviesEntity(
                id = 1,
                title = "title1",
                posterUrl = "posterUrl",
                genreIds = listOf(1, 3, 4, 6),
                backdropUrl = "backdrop",
                overview = "overview",
                rating = 7.7,
                releaseDate = "12/12/2002",
                runtimeMinutes = 140
            ),
            TopRatedMoviesEntity(
                id = 2,
                title = "title2",
                posterUrl = "posterUrl",
                genreIds = listOf(1, 3, 4, 6, 45, 2),
                backdropUrl = "backdrop",
                overview = "overview",
                rating = 7.7,
                releaseDate = "12/12/2002",
                runtimeMinutes = 140
            )
        )
    }

    fun createMoviesGenreEntityList(): List<MoviesGenreEntity> {
        return listOf(
            MoviesGenreEntity(
                genreId = 1,
                name = "title one"
            ),
            MoviesGenreEntity(
                genreId = 2,
                name = "title two"
            )
        )
    }

    fun createMovieEntity(): MoviesWithGenres {
        return MoviesWithGenres(
            genres = createMoviesGenreEntityList(), topRatedMoviesEntity = TopRatedMoviesEntity(
                id = 1,
                title = "title",
                posterUrl = "posterUrl",
                genreIds = listOf(1, 3, 4, 6),
                backdropUrl = "backdrop",
                overview = "overview",
                rating = 7.7,
                releaseDate = "12/12/2002",
                runtimeMinutes = 140
            )
        )
    }

    fun createMovieList(): List<Movie> {
        return listOf(
            Movie(
                id = 1,
                title = "title1",
                posterUrl = "url",
                backdropUrl = "backDrop",
                overview = "overview",
                genreIds = listOf(1),
                rating = 6.7,
                releaseDate = "12/12/2023",
                runtimeMinutes = 200
            ),
            Movie(
                id = 2,
                title = "title2",
                posterUrl = "url",
                backdropUrl = "backDrop",
                overview = "overview",
                genreIds = listOf(2),
                rating = 6.7,
                releaseDate = "12/12/2023",
                runtimeMinutes = 200
            )
        )
    }
}