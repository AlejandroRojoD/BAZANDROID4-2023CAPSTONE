package com.example.wizelineproject.domain.usecases

import com.example.local.entities.Movie
import com.example.wizelineproject.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetMoviesListUseCaseTest {

    private lateinit var sut: GetMoviesListUseCase
    private val repository = mockk<Repository>(relaxed = true)

    @Before
    fun `setUp`() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = GetMoviesListUseCase(repository = repository)
    }

    @Test
    fun `invokeShouldReturnListMoviesOnSuccess`() = runBlocking {
        // Given
        val movies = listOf(
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
                rating = 6.7,
                releaseDate = "12/12/2023",
                runtimeMinutes = 200
            )
        )

        coEvery { repository.getNowPLayingMovies() } returns movies

        // When
        val result = sut()

        // Then
        coVerify(exactly = 1) { repository.getNowPLayingMovies() }
        assertEquals(Result.success(movies), result)
    }
}