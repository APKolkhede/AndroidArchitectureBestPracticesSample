package com.techtest.upcomingmovies.data

class GetUpComingMoviesRepository(private val getUpComingMoviesRemoteDataSource: GetUpComingMoviesRemoteDataSource) {
    suspend fun getUpcomingMovies() = getUpComingMoviesRemoteDataSource.getUpcomingMovies()
}
