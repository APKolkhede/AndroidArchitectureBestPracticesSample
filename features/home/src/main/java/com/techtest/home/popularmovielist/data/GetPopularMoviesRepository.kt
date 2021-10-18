package com.techtest.home.popularmovielist.data

class GetPopularMoviesRepository(private val getPopularMoviesRemoteDataSource: GetPopularMoviesRemoteDataSource) {
    suspend fun getPopularMovies() = getPopularMoviesRemoteDataSource.getPopularMovies()
}
