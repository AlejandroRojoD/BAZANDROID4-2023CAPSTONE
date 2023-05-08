package com.example.wizelineproject.domain.usecases


import com.example.local.entities.Movie
import com.example.wizelineproject.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetLatestMovieUseCaseTest{

    private lateinit var sut: GetLatestMovieUseCase
    private val repository = mockk<Repository>(relaxed = true)

    @Before
    fun `setUp`() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = GetLatestMovieUseCase(repository)
    }

    @Test
    fun `invokeShouldReturnSuccess`() = runBlocking {
        // Given
        val movie = Movie(
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
        coEvery { repository.getLatestMovie() } returns movie

        // When
        val result = sut()

        // Then
        coVerify(exactly = 1) { repository.getLatestMovie() }
        assertTrue(result.isSuccess)
        assertEquals(movie, result.getOrNull())
    }
}