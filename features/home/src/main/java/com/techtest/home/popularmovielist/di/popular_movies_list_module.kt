package com.techtest.home.popularmovielist.di

import com.techtest.home.popularmovielist.data.GetPopularMoviesRemoteDataSource
import com.techtest.home.popularmovielist.data.GetPopularMoviesRepository
import com.techtest.home.popularmovielist.domain.GetPopularMoviesUseCase
import com.techtest.home.popularmovielist.presentation.HomeViewModel
import org.koin.dsl.module

val popular_movies_list_module = module {

    single { HomeViewModel(get()) }

    single { GetPopularMoviesUseCase(get()) }

    single { GetPopularMoviesRepository(get()) }

    single { GetPopularMoviesRemoteDataSource(get()) }
}
