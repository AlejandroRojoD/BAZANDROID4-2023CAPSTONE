package com.example.wizelineproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizelineproject.data.model.MovieListResponse
import com.example.wizelineproject.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {
    private val _stateMovies = MutableLiveData<MovieListResponse>()
    val stateMovies: LiveData<MovieListResponse> get() = _stateMovies

    init {
        viewModelScope.launch {
            _stateMovies.value = moviesRepository.getNowPLayingMovies()
        }
    }
}