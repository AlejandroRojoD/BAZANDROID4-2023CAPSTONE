package com.example.wizelineproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wizelineproject.config.Constants.DataBase.DATABASE_VERSION
import com.example.wizelineproject.data.database.dao.MoviesDao
import com.example.wizelineproject.data.database.entities.*
import com.example.wizelineproject.data.mappers.IntegerListConverter

@Database(
    entities = [
        LatestMovieEntity::class,
        NowPlayingMoviesEntity::class,
        TopRatedMoviesEntity::class,
        MoviesGenreEntity::class], version = DATABASE_VERSION, exportSchema = false
)
@TypeConverters(IntegerListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}