package com.example.wizelineproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wizelineproject.config.Constants.DataBase.DATABASE_VERSION
import com.example.wizelineproject.data.database.dao.MoviesDao
import com.example.wizelineproject.data.database.entities.LatestMovieEntity
import com.example.wizelineproject.data.database.entities.NowPlayingMoviesEntity
import com.example.wizelineproject.data.database.entities.TopRatedMoviesEntity

@Database(
    entities = [
        LatestMovieEntity::class,
        NowPlayingMoviesEntity::class,
        TopRatedMoviesEntity::class], version = DATABASE_VERSION, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}