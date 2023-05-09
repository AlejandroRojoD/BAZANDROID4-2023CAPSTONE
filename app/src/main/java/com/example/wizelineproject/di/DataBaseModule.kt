package com.example.wizelineproject.di

import android.content.Context
import androidx.room.Room
import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.data.database.AppDatabase
import com.example.wizelineproject.data.database.dao.MoviesDao
import com.example.wizelineproject.data.repository.LocalDataSourceImpl
import com.example.wizelineproject.domain.repository.LocalDataSource
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