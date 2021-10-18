package com.techtest.core.network

import com.techtest.core.BuildConfig
import com.techtest.core.responses.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesResponse>

    @GET("/3/movie/upcoming?api_key=${BuildConfig.API_KEY}")
    suspend fun getUpComingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesResponse>
}
