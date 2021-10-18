package com.techtest.home.popularmovielist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.techtest.core.network.NetworkState
import com.techtest.core.responses.Movie
import com.techtest.home.R
import com.techtest.home.databinding.FragmentHomeBinding
import com.techtest.home.popularmovielist.presentation.adapter.MovieListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), MovieListAdapter.OnItemClickListener {
    private val viewModel: HomeViewModel by viewModel()

    private val listAdapter by lazy { MovieListAdapter() }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        startObservers()
        viewModel.init()
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
            is NetworkState.Loading -> binding.loadingIndicator.visibility = View.VISIBLE
        }
    }

    private fun updateMovies(movies: List<Movie>?) {
        listAdapter.submitList(movies)
        binding.loadingIndicator.visibility = View.GONE
    }

    private fun setupUI() {
        binding.popularMoviesList.apply {
            layoutManager =
                GridLayoutManager(
                    this@HomeFragment.context, 2
                )
            listAdapter.setItemClickListener(this@HomeFragment)
            adapter = listAdapter
        }
    }

    private fun showError(message: String) {
        this.context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(message)
                .setPositiveButton(
                    getString(R.string.retry_button)
                ) { dialog, _ ->
                    viewModel.onRetryClick()
                    dialog.dismiss()
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }
        binding.loadingIndicator.visibility = View.GONE
    }

    override fun onItemClick(movie: Movie) {
        val direction = HomeFragmentDirections.actionMovieListToMovieDetail(movie)
        findNavController().navigate(direction)
    }
}
