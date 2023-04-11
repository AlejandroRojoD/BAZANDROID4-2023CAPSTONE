package com.example.wizelineproject.config

import com.example.wizelineproject.data.network.MoviesApi
import com.example.wizelineproject.data.repository.MoviesRepository
import com.example.wizelineproject.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): MoviesApi {
        return builder
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideDogRepository(moviesApi: MoviesApi): MoviesRepository =
        MoviesRepositoryImpl(moviesApi)
}