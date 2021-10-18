package com.techtest.home.popularmovielist.data

import com.nhaarman.mockitokotlin2.verify
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.core.responses.MoviesResponse
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
class GetPopularMoviesRepositoryTest {
    private lateinit var getPopularMoviesRepository: GetPopularMoviesRepository

    @Mock
    lateinit var dataSource: GetPopularMoviesRemoteDataSource

    @Before
    fun setUp() {
        getPopularMoviesRepository = GetPopularMoviesRepository(dataSource)
    }

    @Test
    fun `test Movies api returns success`() {
        runBlocking {
            // given
            val apiResponseJson = MoviesResponse(1, emptyList<Movie>(), 10, 1)
            val response =
                Response.success(apiResponseJson)
            Mockito.`when`(dataSource.getPopularMovies())
                .thenReturn(NetworkState.Success(response.body()?.results))

            // when
            val result = getPopularMoviesRepository.getPopularMovies()

            // then
            verify(dataSource).getPopularMovies()
            assertEquals(result, NetworkState.Success(emptyList<Movie>()))
        }
    }

    @Test
    fun `test Movies api returns error`() {
        runBlocking {
            // given
            Mockito.`when`(dataSource.getPopularMovies()).thenReturn(NetworkState.Failure("Couldn't fetch movies!"))

            // when
            val result = getPopularMoviesRepository.getPopularMovies()

            // then
            verify(dataSource).getPopularMovies()
            assertEquals(result, NetworkState.Failure("Couldn't fetch movies!"))
        }
    }
}
