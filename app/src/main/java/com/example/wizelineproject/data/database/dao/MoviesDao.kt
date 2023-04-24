package com.example.wizelineproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wizelineproject.config.Constants.DataBase.LATEST_MOVIE_TABLE
import com.example.wizelineproject.config.Constants.DataBase.NOW_PLAYING_MOVIES_TABLE
import com.example.wizelineproject.config.Constants.DataBase.TOP_RATED_MOVIES_TABLE
import com.example.wizelineproject.data.database.entities.LatestMovieEntity
import com.example.wizelineproject.data.database.entities.NowPlayingMoviesEntity
import com.example.wizelineproject.data.database.entities.TopRatedMoviesEntity

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
}