package com.techtest.upcomingmovies.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.upcomingmovies.databinding.FragmentUpcomingMoviesBinding
import com.techtest.upcomingmovies.presentation.adapter.MovieListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingMoviesFragment : Fragment() {

    private val viewModel: UpcomingMoviesViewModel by viewModel()

    private val listAdapter by lazy { MovieListAdapter() }

    private var _binding: FragmentUpcomingMoviesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingMoviesBinding.inflate(inflater, container, false)
        setupUI()
        startObservers()
        viewModel.init()
        return binding.root
    }

    private fun startObservers() {
        viewModel.getUpComingMovieSource().observeForever {
            renderUi(it)
        }
    }

    private fun renderUi(result: NetworkState<List<Movie>>?) {
        when (result) {
            is NetworkState.Loading -> binding.progressIndicator.visibility = View.VISIBLE
            is NetworkState.Success -> updateMovies(result.value)
            is NetworkState.Failure -> Log.e("test", "test")
        }
    }

    private fun updateMovies(movies: List<Movie>?) {
        listAdapter.submitList(movies)
        binding.progressIndicator.visibility = View.GONE
    }

    private fun setupUI() {
        binding.upcomingMoviesList.apply {
            layoutManager =
                LinearLayoutManager(
                    this@UpcomingMoviesFragment.context, LinearLayoutManager.VERTICAL, false
                )
            adapter = listAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
