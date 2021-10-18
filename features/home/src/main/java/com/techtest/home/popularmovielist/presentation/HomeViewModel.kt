package com.techtest.home.popularmovielist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.home.popularmovielist.domain.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val moviesUseCase: GetPopularMoviesUseCase) :
    ViewModel() {
    private val moviesSource = MutableLiveData<NetworkState<List<Movie>>>()

    fun getMovieSource() = moviesSource

    fun init() {
        if (moviesSource.value == null) {
            getMovies()
        }
    }

    private fun getMovies() {
        moviesSource.postValue(NetworkState.Loading)
        viewModelScope.launch {
            updateView(moviesUseCase.execute())
        }
    }

    private fun updateView(movies: NetworkState<List<Movie>>) {
        moviesSource.value = movies
    }

    fun onRetryClick() {
        getMovies()
    }
}
