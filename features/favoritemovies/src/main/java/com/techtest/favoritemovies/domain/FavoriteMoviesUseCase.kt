package com.techtest.favoritemovies.domain

import com.techtest.favoritemovies.data.FavoriteMoviesRepository

class FavoriteMoviesUseCase(private val favoriteMoviesRepository: FavoriteMoviesRepository) {
    suspend fun execute() = favoriteMoviesRepository.getFavoriteMovies()
}
