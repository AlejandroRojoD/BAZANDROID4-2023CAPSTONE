package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.Constants.DataBase.DATABASE_VERSION
import com.example.local.dao.MoviesDao
import com.example.local.entities.*

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