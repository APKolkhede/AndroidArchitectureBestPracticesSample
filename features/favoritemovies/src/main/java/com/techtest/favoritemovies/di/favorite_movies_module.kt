package com.techtest.favoritemovies.di

import com.techtest.favoritemovies.data.FavoriteMoviesLocalDataSource
import com.techtest.favoritemovies.data.FavoriteMoviesRepository
import com.techtest.favoritemovies.domain.FavoriteMoviesUseCase
import com.techtest.favoritemovies.presentation.FavoriteMovieViewModel
import org.koin.dsl.module

val favorite_movies_module = module {

    single { FavoriteMovieViewModel(get()) }

    single { FavoriteMoviesUseCase(get()) }

    single { FavoriteMoviesRepository(get()) }

    single { FavoriteMoviesLocalDataSource(get()) }
}
