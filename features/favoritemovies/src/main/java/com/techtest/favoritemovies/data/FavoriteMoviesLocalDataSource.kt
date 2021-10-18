package com.techtest.favoritemovies.data

import com.techtest.core.database.FavoriteMoviesDataBase
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie

class FavoriteMoviesLocalDataSource(private val dataBase: FavoriteMoviesDataBase) {
    suspend fun getFavoriteMovies(): NetworkState<List<Movie>> {
        val movies = dataBase.movieDao().getFavoriteMovies()
        if (movies != null && !movies.isEmpty()) {
            return NetworkState.Success(movies)
        } else {
            return NetworkState.Failure("You don't have any favorite!")
        }
    }
}
