package com.techtest.upcomingmovies.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.upcomingmovies.TestCoroutineRule
import com.techtest.upcomingmovies.domain.GetUpComingMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UpcomingMoviesViewModelTest {
    private lateinit var viewModel: UpcomingMoviesViewModel

    @Mock
    private lateinit var useCase: GetUpComingMoviesUseCase

    @Mock
    private lateinit var movieObserver: Observer<NetworkState<List<Movie>>>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        viewModel = UpcomingMoviesViewModel(useCase)
    }

    @Test
    fun `test init use case returns success`() {
        runBlockingTest {
            // given
            doReturn(NetworkState.Success(emptyList<Movie>()))
                .`when`(useCase)
                .execute()

            // when
            viewModel.init()
            viewModel.getUpComingMovieSource().observeForever(movieObserver)

            // then
            verify(useCase).execute()
            verify(movieObserver).onChanged(NetworkState.Success(emptyList()))
            viewModel.getUpComingMovieSource().removeObserver(movieObserver)
        }
    }

    @Test
    fun `test init use case returns error`() {
        runBlockingTest {
            // given
            doReturn(NetworkState.Failure("test"))
                .`when`(useCase)
                .execute()

            // when
            viewModel.init()
            viewModel.getUpComingMovieSource().observeForever(movieObserver)

            // then
            verify(useCase).execute()
            verify(movieObserver).onChanged(NetworkState.Failure("test"))
            viewModel.getUpComingMovieSource().removeObserver(movieObserver)
        }
    }
}
