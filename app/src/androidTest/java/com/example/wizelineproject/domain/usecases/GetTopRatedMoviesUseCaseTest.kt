package com.example.wizelineproject.domain.usecases

import com.example.local.entities.Movie
import com.example.wizelineproject.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTopRatedMoviesUseCaseTest {
    private lateinit var sut: GetTopRatedMoviesUseCase
    private val repository = mockk<Repository>(relaxed = true)

    @Before
    fun `setUp`() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = GetTopRatedMoviesUseCase(repository = repository)
    }

    @Test
    fun `invokeShouldReturnTopRatedListMoviesOnSuccess`() = runBlocking {
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

        coEvery { repository.getTopRatedMovies() } returns movies

        // When
        val result = sut()

        // Then
        coVerify(exactly = 1) { repository.getTopRatedMovies() }
        assertEquals(Result.success(movies), result)
    }
}