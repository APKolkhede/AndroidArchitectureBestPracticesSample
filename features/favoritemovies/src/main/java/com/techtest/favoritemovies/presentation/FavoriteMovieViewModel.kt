package com.techtest.favoritemovies.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.favoritemovies.domain.FavoriteMoviesUseCase
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val favoriteMoviesUseCase: FavoriteMoviesUseCase) :
    ViewModel() {
    private val moviesSource = MutableLiveData<NetworkState<List<Movie>>>()

    fun getMovieSource() = moviesSource

    fun getMovies() {
        viewModelScope.launch {
            updateView(favoriteMoviesUseCase.execute())
        }
    }

    private fun updateView(movies: NetworkState<List<Movie>>) {
        moviesSource.value = movies
    }
}
