package com.example.wizelineproject.data.repository

import com.example.local.dao.MoviesDao
import com.example.local.repository.LocalDataSourceImpl
import com.example.wizelineproject.utils.Utils.createMovieEntity
import com.example.wizelineproject.utils.Utils.createMovieLatestEntity
import com.example.wizelineproject.utils.Utils.createMoviesGenreEntityList
import com.example.wizelineproject.utils.Utils.createNowPlayingMoviesEntityList
import com.example.wizelineproject.utils.Utils.createTopRatedMoviesEntityList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LocalDataSourceImplTest {
    private lateinit var sut: LocalDataSourceImpl
    private val mockMovieDao = mockk<MoviesDao>(relaxed = true)

    @Before
    fun `setUp`() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = LocalDataSourceImpl(mockMovieDao)
    }

    @Test
    fun `insertLatestMovie should call insertLatestMovie in DAO`() = runBlocking {
        // Given
        val movieEntity = createMovieLatestEntity()

        // When
        sut.insertLatestMovie(movieEntity)

        // Then
        coVerify { mockMovieDao.insertLatestMovie(movieEntity) }
    }

    @Test
    fun `getLatestMovieFromDb should return latest movie from DAO`() = runBlocking {
        // Given
        val latestMovieEntity = createMovieLatestEntity()
        coEvery { mockMovieDao.getLatestMovie() } returns latestMovieEntity

        // When
        val result = sut.getLatestMovieFromDb()

        // Then
        coVerify { mockMovieDao.getLatestMovie() }
        assertEquals(latestMovieEntity, result)
    }

    @Test
    fun `insertNowPlayingMovies should call insertNowPlayingMovies in DAO`() = runBlocking {
        // Given
        val nowPlayingMoviesEntity = createNowPlayingMoviesEntityList()

        // When
        sut.insertNowPlayingMovies(nowPlayingMoviesEntity)

        // Then
        coVerify { mockMovieDao.insertNowPlayingMovies(nowPlayingMoviesEntity) }
    }

    @Test
    fun `getNowPlayingMovies should return nowPlayingMovies in DAO`() = runBlocking {
        // Given
        val nowPlayingMoviesEntity = createNowPlayingMoviesEntityList()
        coEvery { mockMovieDao.getNowPlayingMovies() } returns nowPlayingMoviesEntity

        // When
        val result = sut.getNowPlayingMoviesFromDb()

        // Then
        coVerify { mockMovieDao.getNowPlayingMovies() }
        assertEquals(nowPlayingMoviesEntity, result)
    }

    @Test
    fun `insertTopRatedMovies should call insertTopRatedMovies in DAO`() = runBlocking {
        // Given
        val topRatedMoviesEntity = createTopRatedMoviesEntityList()

        // When
        sut.insertTopRatedMovies(topRatedMoviesEntity)

        // Then
        coVerify { mockMovieDao.insertTopRatedMovies(topRatedMoviesEntity) }
    }

    @Test
    fun `getTopRatedMovies should return topRatedMovies in DAO`() = runBlocking {
        // Given
        val topRatedMoviesEntity = createTopRatedMoviesEntityList()
        coEvery { mockMovieDao.getTopRatedMovies() } returns topRatedMoviesEntity

        // When
        val result = sut.getTopRatedMoviesFromDb()

        // Then
        coVerify { mockMovieDao.getTopRatedMovies() }
        assertEquals(topRatedMoviesEntity, result)
    }

    @Test
    fun `insertMovieGenreList should call insertMovieGenreList in DAO`() = runBlocking {
        // Given
        val movieGenreEntityList = createMoviesGenreEntityList()

        // When
        sut.insertMoviesGenreList(movieGenreEntityList)

        // Then
        coVerify { mockMovieDao.insertMoviesGenreList(movieGenreEntityList) }
    }

    @Test
    fun `getMovieGenreList should return MovieGenreEntityList in DAO`() = runBlocking {
        // Given
        val movieGenreEntityList = createMoviesGenreEntityList()
        coEvery { mockMovieDao.getMoviesGenreList() } returns movieGenreEntityList

        // When
        val result = sut.getMoviesGenreListFromDb()

        // Then
        coVerify { mockMovieDao.getMoviesGenreList() }
        assertEquals(movieGenreEntityList, result)
    }

    @Test
    fun `getGenresForMovie should return movies with genres from DAO`() = runBlocking {
        // Given
        val movieId = 123
        val expectedMoviesWithGenres = createMovieEntity()
        coEvery { mockMovieDao.getMoviesWithGenres(movieId) } returns expectedMoviesWithGenres

        // When
        val result = sut.getGenresForMovie(movieId)

        // Then
        assertEquals(expectedMoviesWithGenres, result)
        coVerify { mockMovieDao.getMoviesWithGenres(movieId) }
    }

}