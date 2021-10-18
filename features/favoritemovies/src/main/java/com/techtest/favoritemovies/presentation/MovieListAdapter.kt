package com.techtest.favoritemovies.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techtest.core.responses.Movie
import com.techtest.favoritemovies.R

class MovieListAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val entry = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_movie, parent, false)
        return MovieViewHolder(entry)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(getItem(position)) {
            holder.bind(this)
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coverView = itemView.findViewById<ImageView>(R.id.movieCover)
    private val movieTitle = itemView.findViewById<TextView>(R.id.upcomingMovieTitle)
    private val movieOverview = itemView.findViewById<TextView>(R.id.upcomingMovieOverview)

    fun bind(item: Movie) {
        movieTitle.text = item.title
        movieOverview.text = item.overview
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original" + item.posterPath)
            .centerCrop().into(coverView)
    }
}
