package com.techtest.upcomingmovies.domain

import com.nhaarman.mockitokotlin2.verify
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.core.responses.MoviesResponse
import com.techtest.upcomingmovies.data.GetUpComingMoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class GetUpComingMoviesUseCaseTest {
    private lateinit var getMoviesUseCase: GetUpComingMoviesUseCase

    @Mock
    lateinit var repository: GetUpComingMoviesRepository

    @Before
    fun setUp() {
        getMoviesUseCase = GetUpComingMoviesUseCase(repository)
    }

    @Test
    fun `test Movies api returns success`() {
        runBlocking {
            // given
            val apiResponseJson = MoviesResponse(1, emptyList<Movie>(), 10, 1)
            val response =
                Response.success(apiResponseJson)
            Mockito.`when`(repository.getUpcomingMovies())
                .thenReturn(NetworkState.Success(response.body()?.results))

            // when
            val result = getMoviesUseCase.execute()

            // then
            verify(repository).getUpcomingMovies()
            assertEquals(result, NetworkState.Success(emptyList<Movie>()))
        }
    }

    @Test
    fun `test Movies api returns error`() {
        runBlocking {
            // given
            Mockito.`when`(repository.getUpcomingMovies()).thenReturn(NetworkState.Failure("Couldn't fetch movies!"))

            // when
            val result = getMoviesUseCase.execute()

            // then
            verify(repository).getUpcomingMovies()
            assertEquals(result, NetworkState.Failure("Couldn't fetch movies!"))
        }
    }
}
