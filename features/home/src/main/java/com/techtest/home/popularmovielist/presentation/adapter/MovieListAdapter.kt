package com.techtest.home.popularmovielist.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techtest.core.responses.Movie
import com.techtest.home.R

class MovieListAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {
    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val entry = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(entry)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(getItem(position)) {
            holder.bind(this, clickListener)
        }
    }

    fun setItemClickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
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

    fun bind(item: Movie, clickListener: MovieListAdapter.OnItemClickListener) {
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original" + item.posterPath)
            .centerCrop().into(coverView)
        itemView.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }
}
