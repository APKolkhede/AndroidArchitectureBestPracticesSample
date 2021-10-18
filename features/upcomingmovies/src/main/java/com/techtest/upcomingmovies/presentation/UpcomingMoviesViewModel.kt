package com.techtest.upcomingmovies.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.upcomingmovies.domain.GetUpComingMoviesUseCase
import kotlinx.coroutines.launch

class UpcomingMoviesViewModel(private val useCase: GetUpComingMoviesUseCase) :
    ViewModel() {
    private val moviesSource = MutableLiveData<NetworkState<List<Movie>>>()

    fun getUpComingMovieSource() = moviesSource

    fun init() {
        if (moviesSource.value == null) {
            getUpComingMovies()
        }
    }

    private fun getUpComingMovies() {
        moviesSource.postValue(NetworkState.Loading)
        viewModelScope.launch {
            updateView(useCase.execute())
        }
    }

    private fun updateView(movies: NetworkState<List<Movie>>) {
        moviesSource.value = movies
    }
}
