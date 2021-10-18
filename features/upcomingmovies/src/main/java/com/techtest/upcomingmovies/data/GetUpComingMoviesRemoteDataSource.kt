package com.techtest.upcomingmovies.data

import com.techtest.core.network.MovieApi
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie

class GetUpComingMoviesRemoteDataSource(private val movieApi: MovieApi) {
    suspend fun getUpcomingMovies(): NetworkState<List<Movie>> {
        val response = movieApi.getUpComingMovies()
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
