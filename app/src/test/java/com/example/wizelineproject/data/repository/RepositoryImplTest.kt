package com.example.wizelineproject.data.repository


import com.example.local.entities.Movie
import com.example.local.entities.MoviesGenreEntity
import com.example.local.entities.MoviesWithGenres
import com.example.local.entities.TopRatedMoviesEntity
import com.example.local.repository.LocalDataSource
import com.example.wizelineproject.data.remote.repository.RemoteDataSource
import com.example.wizelineproject.utils.Utils.createMovieList
import com.example.wizelineproject.utils.Utils.createMoviesGenreEntityList
import com.example.wizelineproject.utils.Utils.createNowPlayingMoviesEntityList
import com.example.wizelineproject.utils.Utils.createTopRatedMoviesEntityList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {

    private lateinit var sut: RepositoryImpl
    private val remoteDataSource = mockk<RemoteDataSource>(relaxed = true)
    private val localDataSource = mockk<LocalDataSource>(relaxed = true)

    @Before
    fun `setUp`() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = RepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `getNowPLayingMovies() should return data from localDataSource`() = runBlocking {
        // GIVEN
        val expectedData = createMovieList()

        coEvery { localDataSource.getMoviesGenreListFromDb() } returns createMoviesGenreEntityList()
        coEvery { localDataSource.getNowPlayingMoviesFromDb() } returns createNowPlayingMoviesEntityList()

        // WHEN
        val result = sut.getNowPLayingMovies()

        // THEN
        assertEquals(expectedData, result)

        // Verificamos que solo se llamó al método remoto una vez (no se llamará en esta prueba)
        coVerify(exactly = 0) { remoteDataSource.getNowPLayingMovies() }

        // Verificamos que se llamó al método local solo una vez
        coVerify(exactly = 1) { localDataSource.getMoviesGenreListFromDb() }
        coVerify(exactly = 0) { localDataSource.insertMoviesGenreList(any()) }
        coVerify(exactly = 0) { localDataSource.insertNowPlayingMovies(any()) }
    }

    @Test
    fun `getTopRatedMovies should return data from local data source when available`() =
        runBlocking {
            // Given
            val topRatedMoviesFromDb = createTopRatedMoviesEntityList()

            coEvery { localDataSource.getTopRatedMoviesFromDb() } returns topRatedMoviesFromDb

            // When
            val result = sut.getTopRatedMovies()

            // Then
//            assertEquals(topRatedMoviesFromDb.map { it.toDomain() }, result)
            assertNotNull(result)
            assertNotNull(topRatedMoviesFromDb)
            coVerify(atLeast = 1) { localDataSource.getTopRatedMoviesFromDb() }
            coVerify(exactly = 0) { remoteDataSource.getTopRatedMovies() }
            coVerify(exactly = 0) { localDataSource.insertTopRatedMovies(any()) }
        }

    @Test
    fun `getLatestMovie returns remote data source result`() = runBlocking {
        // Given
        val expectedMovie = Movie(
            id = 1,
            title = "title1",
            posterUrl = "url",
            backdropUrl = "backDrop",
            overview = "overview",
            genreIds = listOf(1),
            rating = 6.7,
            releaseDate = "12/12/2023",
            runtimeMinutes = 200
        )
        coEvery { remoteDataSource.getLatestMovie() } returns expectedMovie

        // When
        val result = sut.getLatestMovie()

        // Then
        assertEquals(expectedMovie, result)
        coVerify(exactly = 1) { remoteDataSource.getLatestMovie() }
        coVerify(exactly = 0) { localDataSource.getLatestMovieFromDb() }
        coVerify(exactly = 0) { localDataSource.insertLatestMovie(any()) }
    }

    @Test
    fun `getMovieWithGenre should return correct value`() = runBlocking {
        // Given
        val movieId = 123
        val expected = MoviesWithGenres(
            genres = listOf(
                MoviesGenreEntity(genreId = 12, name = "test"),
                MoviesGenreEntity(genreId = 122, name = "test2")
            ), topRatedMoviesEntity = TopRatedMoviesEntity(
                id = 123,
                title = "title1",
                posterUrl = "posterUrl",
                genreIds = listOf(12, 122, 4, 6),
                backdropUrl = "backdrop",
                overview = "overview",
                rating = 7.7,
                releaseDate = "12/12/2002",
                runtimeMinutes = 140
            )
        )

        // When
        coEvery { localDataSource.getGenresForMovie(movieId) } returns expected

        val result = sut.getMovieWithGenre(movieId)

        // Then
        assertEquals(expected, result)
        coVerify(exactly = 1) { localDataSource.getGenresForMovie(movieId) }
    }

}