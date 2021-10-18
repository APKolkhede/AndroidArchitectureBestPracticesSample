package com.techtest.home.popularmovielist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.home.TestCoroutineRule
import com.techtest.home.popularmovielist.domain.GetPopularMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var useCase: GetPopularMoviesUseCase

    @Mock
    private lateinit var movieObserver: Observer<NetworkState<List<Movie>>>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun `test init use case returns success`() {
        runBlockingTest {
            // given
            Mockito.doReturn(NetworkState.Success(emptyList<Movie>()))
                .`when`(useCase)
                .execute()

            // when
            viewModel.init()
            viewModel.getMovieSource().observeForever(movieObserver)

            // then
            verify(useCase).execute()
            verify(movieObserver).onChanged(NetworkState.Success(emptyList()))
            viewModel.getMovieSource().removeObserver(movieObserver)
        }
    }

    @Test
    fun `test init use case returns error`() {
        runBlockingTest {
            // given
            Mockito.doReturn(NetworkState.Failure("test"))
                .`when`(useCase)
                .execute()

            // when
            viewModel.init()
            viewModel.getMovieSource().observeForever(movieObserver)

            // then
            verify(useCase).execute()
            verify(movieObserver).onChanged(NetworkState.Failure("test"))
            viewModel.getMovieSource().removeObserver(movieObserver)
        }
    }

    @Test
    fun `test retry use case returns success`() {
        runBlockingTest {
            // given
            Mockito.doReturn(NetworkState.Success(emptyList<Movie>()))
                .`when`(useCase)
                .execute()

            // when
            viewModel.onRetryClick()
            viewModel.getMovieSource().observeForever(movieObserver)
            // then
            verify(useCase).execute()
            verify(movieObserver).onChanged(NetworkState.Success(emptyList()))
            viewModel.getMovieSource().removeObserver(movieObserver)
        }
    }

    @Test
    fun `test retry use case returns error`() {
        runBlockingTest {
            // given
            Mockito.doReturn(NetworkState.Failure("test"))
                .`when`(useCase)
                .execute()

            // when
            viewModel.onRetryClick()
            viewModel.getMovieSource().observeForever(movieObserver)

            // then
            verify(useCase).execute()
            verify(movieObserver).onChanged(NetworkState.Failure("test"))
            viewModel.getMovieSource().removeObserver(movieObserver)
        }
    }
}
