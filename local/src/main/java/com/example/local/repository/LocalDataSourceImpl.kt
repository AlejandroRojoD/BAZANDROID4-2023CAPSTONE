package com.example.local.repository

import com.example.local.dao.MoviesDao
import com.example.local.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val moviesDao: MoviesDao
) : LocalDataSource {
    override suspend fun insertLatestMovie(latestMovieEntity: LatestMovieEntity) {
        moviesDao.insertLatestMovie(latestMovieEntity)
    }

    override suspend fun getLatestMovieFromDb(): LatestMovieEntity =
        moviesDao.getLatestMovie()

    override suspend fun insertNowPlayingMovies(nowPlayingMoviesEntity: List<NowPlayingMoviesEntity>) {
        moviesDao.insertNowPlayingMovies(nowPlayingMoviesEntity)
    }

    override suspend fun getNowPlayingMoviesFromDb(): List<NowPlayingMoviesEntity> =
        moviesDao.getNowPlayingMovies()

    override suspend fun insertTopRatedMovies(topRatedMoviesEntity: List<TopRatedMoviesEntity>) {
        moviesDao.insertTopRatedMovies(topRatedMoviesEntity)
    }

    override suspend fun getTopRatedMoviesFromDb(): List<TopRatedMoviesEntity> =
        moviesDao.getTopRatedMovies()

    override suspend fun insertMoviesGenreList(moviesGenreEntity: List<MoviesGenreEntity>) {
        moviesDao.insertMoviesGenreList(moviesGenreEntity)
    }

    override suspend fun getMoviesGenreListFromDb(): List<MoviesGenreEntity> =
        moviesDao.getMoviesGenreList()

    override suspend fun getGenresForMovie(movieId: Int): MoviesWithGenres {
        return withContext(Dispatchers.IO) {
            //TODO Actualmente esta opcion regresa la lista de pelicuals solo cuando la lista de ids es de 1
            moviesDao.getMoviesWithGenres(movieId = movieId)

            //TODO Encontre como opcion dos un Query mas complejo, pero requiere contruir tabla temporal, tampoco funciono
            /*moviesDao.createSplitIdsTable()
            val movie = moviesDao.getMovie(movieId)
            movie.genreIds.forEach { generoId ->
                moviesDao.insertIds(generoId.toString())
            }
            moviesDao.getPeliculaConGeneros(peliculaId = movieId)*/
        }
    }
}