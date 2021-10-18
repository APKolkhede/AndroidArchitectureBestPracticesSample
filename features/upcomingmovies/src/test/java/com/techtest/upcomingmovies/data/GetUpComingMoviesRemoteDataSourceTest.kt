package com.techtest.upcomingmovies.data

import com.nhaarman.mockitokotlin2.verify
import com.techtest.core.network.MovieApi
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.core.responses.MoviesResponse
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class GetUpComingMoviesRemoteDataSourceTest {
    private lateinit var getUpComingMoviesRemoteDataSource: GetUpComingMoviesRemoteDataSource

    @Mock
    lateinit var api: MovieApi

    @Before
    fun setUp() {
        getUpComingMoviesRemoteDataSource = GetUpComingMoviesRemoteDataSource(api)
    }

    @Test
    fun `test Movies api returns success`() {
        runBlocking {
            // given
            val apiResponseJson = MoviesResponse(1, emptyList<Movie>(), 10, 1)
            val response =
                Response.success(apiResponseJson)
            Mockito.`when`(api.getUpComingMovies())
                .thenReturn(response)

            // when
            val result = getUpComingMoviesRemoteDataSource.getUpcomingMovies()

            // then
            verify(api).getUpComingMovies()
            assertEquals(result, NetworkState.Success(emptyList<Movie>()))
        }
    }

    @Test
    fun `test Movies api didn't return response`() {
        runBlocking {
            // given
            Mockito.`when`(api.getUpComingMovies())
                .thenReturn(null)

            // when
            val result = getUpComingMoviesRemoteDataSource.getUpcomingMovies()

            // then
            verify(api).getUpComingMovies()
            assertEquals(result, NetworkState.Failure("Couldn't fetch movies!"))
        }
    }

    @Test
    fun `test Movies api returns error`() {
        runBlocking {
            // given
            val errorResponse = "{\n" +
                "  \"success\": false,\n" +
                "  \"status_code\": 7,\n" +
                "  \"status_message\": \"Invalid API key: You must be granted a valid key.\"\n" +
                "}"
            val error = Response.error<MoviesResponse>(
                401,
                errorResponse.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
            Mockito.`when`(api.getUpComingMovies()).thenReturn(error)

            // when
            val result = getUpComingMoviesRemoteDataSource.getUpcomingMovies()

            // then
            verify(api).getUpComingMovies()
            assertEquals(result, NetworkState.Failure("Couldn't fetch movies!"))
        }
    }
}
