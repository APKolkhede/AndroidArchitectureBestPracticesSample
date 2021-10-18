package com.techtest.home.moviedetails.data

import com.techtest.core.database.FavoriteMoviesDataBase
import com.techtest.core.responses.Movie

class MovieDetailsRepository(private val moviesDataBase: FavoriteMoviesDataBase) {
    suspend fun addToFavorites(movie: Movie) = moviesDataBase.movieDao().addFavoriteMovie(movie)
}
