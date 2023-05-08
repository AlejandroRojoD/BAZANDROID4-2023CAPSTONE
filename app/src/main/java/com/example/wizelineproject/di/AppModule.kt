package com.example.wizelineproject.di

import com.example.local.repository.LocalDataSource
import com.example.wizelineproject.data.remote.repository.RemoteDataSource
import com.example.wizelineproject.data.repository.RepositoryImpl
import com.example.wizelineproject.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): Repository =
        RepositoryImpl(remoteDataSource, localDataSource)
}