package com.techtest.favoritemovies.data

class FavoriteMoviesRepository(private val favoriteMoviesLocalDataSource: FavoriteMoviesLocalDataSource) {
    suspend fun getFavoriteMovies() = favoriteMoviesLocalDataSource.getFavoriteMovies()
}
