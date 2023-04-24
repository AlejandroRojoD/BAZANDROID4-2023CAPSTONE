package com.example.wizelineproject.di

import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.config.HeaderInterceptor
import com.example.wizelineproject.data.network.MoviesApi
import com.example.wizelineproject.data.repository.MoviesRepositoryImpl
import com.example.wizelineproject.domain.repository.MoviesRepository
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
    fun provideDogRepository(moviesApi: MoviesApi): MoviesRepository =
        MoviesRepositoryImpl(moviesApi)

}