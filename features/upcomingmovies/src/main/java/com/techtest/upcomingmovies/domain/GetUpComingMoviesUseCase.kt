package com.techtest.upcomingmovies.domain

import com.techtest.upcomingmovies.data.GetUpComingMoviesRepository

class GetUpComingMoviesUseCase(private val repository: GetUpComingMoviesRepository) {
    suspend fun execute() = repository.getUpcomingMovies()
}
