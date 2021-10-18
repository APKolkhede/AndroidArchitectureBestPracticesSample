package com.techtest.home.popularmovielist.data

import com.techtest.core.network.MovieApi
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie

class GetPopularMoviesRemoteDataSource(private val movieApi: MovieApi) {
    suspend fun getPopularMovies(): NetworkState<List<Movie>> {
        val response = movieApi.getPopularMovies()
        return try {
            if (response.isSuccessful) {
                NetworkState.Success(response.body()?.results)
            } else {
                NetworkState.Failure("Couldn't fetch movies!")
            }
        } catch (t: Throwable) {
            NetworkState.Failure("Couldn't fetch movies!")
        }
    }
}
