package com.techtest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techtest.core.responses.Movie

@Database(entities = [Movie::class], version = 1)
abstract class FavoriteMoviesDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
