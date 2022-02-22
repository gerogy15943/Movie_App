package com.example.testsolution.presentation.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testsolution.R
import com.example.testsolution.data.model.Movie
import com.example.testsolution.databinding.MovieItemBinding
import com.example.testsolution.domain.model.MovieDomain
import com.squareup.picasso.Picasso

class MoviesAdapter: PagingDataAdapter<MovieDomain, MoviesAdapter.MoviesViewHolder>(
    CharacterComparator()
) {
    class MoviesViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieDomain){
            binding.textName.text = movie.title
            binding.textDesc.text = movie.description
            Picasso.get().load(movie.image_url).into(binding.imageView)
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    class CharacterComparator: DiffUtil.ItemCallback<MovieDomain>() {
        override fun areItemsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
            return oldItem == newItem
        }

    }
}