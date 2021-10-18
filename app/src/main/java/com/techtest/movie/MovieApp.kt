package com.techtest.movie

import android.app.Application
import com.techtest.core.database.database_module
import com.techtest.core.network.di.network_module
import com.techtest.favoritemovies.di.favorite_movies_module
import com.techtest.home.moviedetails.di.movie_details_module
import com.techtest.home.popularmovielist.di.popular_movies_list_module
import com.techtest.upcomingmovies.di.upcoming_movies_module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MovieApp)
            modules(
                network_module + database_module + popular_movies_list_module + movie_details_module + upcoming_movies_module + favorite_movies_module
            )
        }
    }
}
