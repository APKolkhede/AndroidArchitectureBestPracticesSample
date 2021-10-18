package com.techtest.home.moviedetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.techtest.home.databinding.FragmentMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        this@MovieDetailsFragment.context?.let {
            Glide.with(it).load("https://image.tmdb.org/t/p/original" + args.movie.posterPath)
                .centerCrop().into(binding.movieCover)
        }
        binding.upcomingMovieOverview.text = args.movie.overview
        binding.upcomingMovieTitle.text = args.movie.title
        binding.fab.setOnClickListener {
            movieDetailsViewModel.addToFavorites(args.movie)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsViewModel.moviesSource.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val snackbar = Snackbar
                    .make(
                        binding.movieDetailsContainer,
                        it,
                        Snackbar.LENGTH_LONG
                    )
                snackbar.show()
            }
        }
    }
}
