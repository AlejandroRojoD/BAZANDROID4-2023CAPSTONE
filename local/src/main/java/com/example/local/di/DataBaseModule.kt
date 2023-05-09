package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.local.AppDatabase
import com.example.local.Constants
import com.example.local.dao.MoviesDao
import com.example.local.repository.LocalDataSource
import com.example.local.repository.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DataBase.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideMoviesDao(database: AppDatabase) = database.getMoviesDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(moviesDao: MoviesDao): LocalDataSource =
        LocalDataSourceImpl(moviesDao)
}