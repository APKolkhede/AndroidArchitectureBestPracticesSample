package com.techtest.upcomingmovies.di

import com.techtest.upcomingmovies.data.GetUpComingMoviesRemoteDataSource
import com.techtest.upcomingmovies.data.GetUpComingMoviesRepository
import com.techtest.upcomingmovies.domain.GetUpComingMoviesUseCase
import com.techtest.upcomingmovies.presentation.UpcomingMoviesViewModel
import org.koin.dsl.module

val upcoming_movies_module = module {

    single { UpcomingMoviesViewModel(get()) }

    single { GetUpComingMoviesUseCase(get()) }

    single { GetUpComingMoviesRepository(get()) }

    single { GetUpComingMoviesRemoteDataSource(get()) }
}
