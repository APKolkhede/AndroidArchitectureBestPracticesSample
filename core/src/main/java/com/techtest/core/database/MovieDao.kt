package com.techtest.core.database

import androidx.room.*
import com.techtest.core.responses.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movies")
    suspend fun getFavoriteMovies(): MutableList<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(movie: Movie)
}
