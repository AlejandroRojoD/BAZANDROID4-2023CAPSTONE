package com.example.wizelineproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizelineproject.config.DataState
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.domain.usecases.GetLatestMovieUseCase
import com.example.wizelineproject.domain.usecases.GetMoviesListUseCase
import com.example.wizelineproject.domain.usecases.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getTopRatedMoviesListUseCase: GetTopRatedMoviesUseCase,
    private val getLatestMovieUseCase: GetLatestMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DataState())
    val uiState = _uiState.asStateFlow()

    fun getNowPlayingMovies() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val moviesResult = getMoviesListUseCase()
        moviesResult.fold(
            onSuccess = { data ->
                _uiState.update {
                    it.copy(isLoading = false, moviesList = data)
                }
            },
            onFailure = { e ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        )
    }

    fun getTopRatedMovies() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val moviesResult = getTopRatedMoviesListUseCase()
        moviesResult.fold(
            onSuccess = { data ->
                _uiState.update {
                    it.copy(isLoading = false, moviesList = data)
                }
            },
            onFailure = { e ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        )
    }

    fun getLatestMovie() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val movieResult: Result<Movie> = getLatestMovieUseCase.invoke()
        movieResult.fold(
            onSuccess = { data ->
                _uiState.update {
                    it.copy(isLoading = false, latestMovie = data)
                }
            },
            onFailure = { e ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        )
    }
}