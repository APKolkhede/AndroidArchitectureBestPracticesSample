package com.techtest.home.popularmovielist.domain

import com.techtest.home.popularmovielist.data.GetPopularMoviesRepository

class GetPopularMoviesUseCase(private val repository: GetPopularMoviesRepository) {
    suspend fun execute() = repository.getPopularMovies()
}
