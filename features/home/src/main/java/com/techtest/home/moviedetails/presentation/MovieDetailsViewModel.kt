package com.techtest.home.moviedetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techtest.core.responses.Movie
import com.techtest.home.moviedetails.data.MovieDetailsRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieDetailsRepository: MovieDetailsRepository) :
    ViewModel() {
    val moviesSource = MutableLiveData<Event<String>>()

    fun addToFavorites(movie: Movie) {
        viewModelScope.launch {
            movieDetailsRepository.addToFavorites(movie)
            moviesSource.postValue(Event("Movie added to Favorites!"))
        }
    }
}
