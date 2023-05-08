package com.example.local.dao

import androidx.room.*
import com.example.local.Constants.DataBase.GENRE_MOVIES_TABLE
import com.example.local.Constants.DataBase.LATEST_MOVIE_TABLE
import com.example.local.Constants.DataBase.NOW_PLAYING_MOVIES_TABLE
import com.example.local.Constants.DataBase.TOP_RATED_MOVIES_TABLE
import com.example.local.entities.*

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLatestMovie(LatestMovie: LatestMovieEntity)

    @Query("SELECT * FROM $LATEST_MOVIE_TABLE")
    suspend fun getLatestMovie(): LatestMovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(nowPlayingMoviesEntity: List<NowPlayingMoviesEntity>)

    @Query("SELECT * FROM $NOW_PLAYING_MOVIES_TABLE")
    suspend fun getNowPlayingMovies(): List<NowPlayingMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(topRatedMoviesEntity: List<TopRatedMoviesEntity>)

    @Query("SELECT * FROM $TOP_RATED_MOVIES_TABLE")
    suspend fun getTopRatedMovies(): List<TopRatedMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesGenreList(moviesGenreList: List<MoviesGenreEntity>)

    @Query("SELECT * FROM $GENRE_MOVIES_TABLE")
    suspend fun getMoviesGenreList(): List<MoviesGenreEntity>


    //TODO funciona pero solo cuando la lista tiene un solo elemento
    @Transaction
    @Query("SELECT * FROM $TOP_RATED_MOVIES_TABLE WHERE id = :movieId")
    fun getMoviesWithGenres(movieId: Int): MoviesWithGenres


    //TODO se requieren mucho Query para conseguir de otra manera, y aun asi no funciono
    /*@Query("SELECT $TOP_RATED_MOVIES_TABLE.*, GROUP_CONCAT($GENRE_MOVIES_TABLE.name) AS nombres_generos " +
            "FROM $TOP_RATED_MOVIES_TABLE " +
            "LEFT JOIN $GENRE_MOVIES_TABLE ON $GENRE_MOVIES_TABLE.genreId IN (SELECT * FROM splitIds($TOP_RATED_MOVIES_TABLE.genreIds)) " +
            "WHERE $TOP_RATED_MOVIES_TABLE.id = :peliculaId " +
            "GROUP BY $TOP_RATED_MOVIES_TABLE.id")
    fun getPeliculaConGeneros(peliculaId: Int): MoviesWithGenres

    @Query("SELECT value FROM regexp_split_to_table(:ids, ',') AS value")
    fun splitIds(ids: String): List<String>

    @Query("CREATE TEMP TABLE IF NOT EXISTS splitIds(id INT)")
    fun createSplitIdsTable()


    @Query("INSERT INTO splitIds(id) SELECT CAST(value AS INTEGER) FROM regexp_split_to_table(:ids, ',') AS value")
    fun insertIds(ids: String)

    @Query("SELECT * FROM $TOP_RATED_MOVIES_TABLE WHERE id = :idMovie")
    fun getMovie(idMovie: Int): TopRatedMoviesEntity*/
}