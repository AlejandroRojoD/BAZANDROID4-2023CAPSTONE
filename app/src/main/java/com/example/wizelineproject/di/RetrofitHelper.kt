package com.example.wizelineproject.di

import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.config.HeaderInterceptor
import com.example.wizelineproject.data.network.MoviesApi
import com.example.wizelineproject.data.repository.RemoteDataSourceImpl
import com.example.wizelineproject.data.repository.RepositoryImpl
import com.example.wizelineproject.domain.repository.LocalDataSource
import com.example.wizelineproject.domain.repository.RemoteDataSource
import com.example.wizelineproject.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MoviesApi {
        return retrofit
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(HeaderInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(moviesApi: MoviesApi): RemoteDataSource =
        RemoteDataSourceImpl(moviesApi)


    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): Repository =
        RepositoryImpl(remoteDataSource, localDataSource)

}