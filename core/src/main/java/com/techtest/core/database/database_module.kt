package com.techtest.core.database

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val database_module = module {
    fun provideDatabase(application: Application): FavoriteMoviesDataBase {
        return Room.databaseBuilder(
            application,
            FavoriteMoviesDataBase::class.java,
            "favoritemovies"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMoviesDao(database: FavoriteMoviesDataBase): MovieDao {
        return database.movieDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideMoviesDao(get()) }
}
