package com.techtest.favoritemovies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.favoritemovies.databinding.FragmentFavoriteMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteMoviesBinding
    private val viewModel: FavoriteMovieViewModel by viewModel()
    private val listAdapter by lazy { MovieListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        startObservers()
        viewModel.getMovies()
    }

    private fun startObservers() {
        viewModel.getMovieSource().observeForever {
            renderUi(it)
        }
    }

    private fun renderUi(result: NetworkState<List<Movie>>?) {
        when (result) {
            is NetworkState.Success -> updateMovies(result.value)
            is NetworkState.Failure -> showError(result.error)
        }
    }

    private fun showError(error: String) {
        binding.errorText.text = error
        binding.errorText.visibility = View.VISIBLE
    }

    private fun updateMovies(movies: List<Movie>?) {
        binding.errorText.visibility = View.GONE
        listAdapter.submitList(movies)
    }

    private fun setupUI() {
        binding.favoriteMoviesList.apply {
            layoutManager =
                GridLayoutManager(
                    this@FavoriteMoviesFragment.context, 2
                )
            adapter = listAdapter
        }
    }
}
