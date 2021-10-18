package com.techtest.home.moviedetails.di

import com.techtest.home.moviedetails.data.MovieDetailsRepository
import com.techtest.home.moviedetails.presentation.MovieDetailsViewModel
import org.koin.dsl.module

val movie_details_module = module {

    single { MovieDetailsViewModel(get()) }

    single { MovieDetailsRepository(get()) }
}
