package com.example.wizelineproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.local.entities.Movie
import com.example.local.entities.MoviesWithGenres
import com.example.wizelineproject.config.DataState
import com.example.wizelineproject.domain.usecases.GetLatestMovieUseCase
import com.example.wizelineproject.domain.usecases.GetMovieWithGenresUseCase
import com.example.wizelineproject.domain.usecases.GetMoviesListUseCase
import com.example.wizelineproject.domain.usecases.GetTopRatedMoviesUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getTopRatedMoviesListUseCase: GetTopRatedMoviesUseCase,
    private val getLatestMovieUseCase: GetLatestMovieUseCase,
    private val getMovieWithGenresUseCase: GetMovieWithGenresUseCase
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

    fun getMovieWithGenres(currentMovieId: Int) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val movieResult: Result<MoviesWithGenres> =
            getMovieWithGenresUseCase.getMovieWithGenres(currentMovieId)
        movieResult.fold(
            onSuccess = { data ->
                _uiState.update {
                    //TODO Temporal Hardcode List
                    it.copy(
                        isLoading = false,
//                        genreList = data.genres.map { genreEntity -> genreEntity.name })
                        genreList = listOf("Drama","Horror", "Accion"))
                }
            },
            onFailure = { e ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        )
    }

    fun doLogin(user: String, pass: String, auth: FirebaseAuth): Observable<Task<AuthResult>>? {
        return if (user.isNotEmpty() && pass.isNotEmpty()) {
            Observable.fromCallable {
                auth.signInWithEmailAndPassword(user, pass)
            }
        } else {
            null
        }
    }
}