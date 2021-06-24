package net.simplifiedcoding.multiviewlist.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import net.simplifiedcoding.multiviewlist.R
import net.simplifiedcoding.multiviewlist.databinding.ItemDirectorBinding
import net.simplifiedcoding.multiviewlist.databinding.ItemMovieBinding
import net.simplifiedcoding.multiviewlist.databinding.ItemTitleBinding

sealed class HomeRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class TitleViewHolder(private val binding: ItemTitleBinding) : HomeRecyclerViewHolder(binding){
        fun bind(title: HomeRecyclerViewItem.Title){
            binding.textViewTitle.text = title.title
        }
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : HomeRecyclerViewHolder(binding){
        fun bind(movie: HomeRecyclerViewItem.Movie){
            binding.imageViewMovie.loadImage(movie.thumbnail)
        }
    }

    class DirectorViewHolder(private val binding: ItemDirectorBinding) : HomeRecyclerViewHolder(binding){
        fun bind(director: HomeRecyclerViewItem.Director){
            binding.imageViewDirector.loadImage(director.avatar)
            binding.textViewName.text = director.name
            binding.textViewMovies.text = binding.textViewMovies.context.getString(R.string.total_movies, director.movie_count)
        }
    }
}