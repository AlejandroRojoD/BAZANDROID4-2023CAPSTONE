package com.example.wizelineproject.presentation

import androidx.lifecycle.MutableLiveData
import com.example.local.entities.Movie
import com.example.wizelineproject.config.DataState
import com.example.wizelineproject.domain.usecases.GetLatestMovieUseCase
import com.example.wizelineproject.domain.usecases.GetMovieWithGenresUseCase
import com.example.wizelineproject.domain.usecases.GetMoviesListUseCase
import com.example.wizelineproject.domain.usecases.GetTopRatedMoviesUseCase
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {
    private lateinit var sut: MoviesViewModel
    private val getMoviesListUseCase = mockk<GetMoviesListUseCase>(relaxed = true)
    private val getTopRatedMoviesListUseCase = mockk<GetTopRatedMoviesUseCase>(relaxed = true)
    private val getLatestMovieUseCase = mockk<GetLatestMovieUseCase>(relaxed = true)
    private val getMovieWithGenresUseCase = mockk<GetMovieWithGenresUseCase>(relaxed = true)
    private val uiState: MutableLiveData<DataState> = spyk()

    private val moviesList = listOf(
        Movie(
            id = 1,
            title = "title1",
            posterUrl = "url",
            backdropUrl = "backDrop",
            overview = "overview",
            genreIds = listOf(1),
            rating = 6.7,
            releaseDate = "12/12/2023",
            runtimeMinutes = 200
        ),
        Movie(
            id = 2,
            title = "title2",
            posterUrl = "url",
            backdropUrl = "backDrop",
            overview = "overview",
            genreIds = listOf(2),
            rating = 6.2,
            releaseDate = "12/12/2023",
            runtimeMinutes = 220
        ),
        Movie(
            id = 3,
            title = "title3",
            posterUrl = "url",
            backdropUrl = "backDrop",
            overview = "overview",
            genreIds = listOf(3),
            rating = 6.3,
            releaseDate = "12/12/2023",
            runtimeMinutes = 203
        )
    )
    private val error = Exception("Error al obtener las películas")

    @Before
    fun setup() {
        sut = MoviesViewModel(
            getMoviesListUseCase,
            getTopRatedMoviesListUseCase,
            getLatestMovieUseCase,
            getMovieWithGenresUseCase
        )
    }

    @Test
    fun `whenetNowPlayingMoviesCalledAndSucceed`() = runBlocking {
        // Configuramos el comportamiento del mock getMoviesListUseCase
        coEvery { getMoviesListUseCase() } returns Result.success(moviesList)

        // Llamamos a la función a testear
        sut.getNowPlayingMovies()

        // Verificamos que se actualizó el uiState correctamente
        verify { uiState.value = DataState(isLoading = true) }
        verify { uiState.value = DataState(isLoading = false, moviesList = moviesList) }
        verify(exactly = 0) { uiState.value = DataState(isLoading = false, errorMessage = any()) }

        // Verificamos que no se lanzó ninguna excepción
        coVerify { getMoviesListUseCase() }
        confirmVerified(getMoviesListUseCase)
    }


}