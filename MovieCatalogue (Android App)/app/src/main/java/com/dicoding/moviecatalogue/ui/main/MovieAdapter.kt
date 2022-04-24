package com.dicoding.moviecatalogue.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.databinding.ItemMoviesBinding
import com.dicoding.moviecatalogue.ui.detail.DetailMovieActivity

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var listMovie = ArrayList<MovieEntity>()

    class MovieViewHolder(private val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.name
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.image)
                    .into(imgMovie)
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_ID, movie.id)
                    intent.putExtra(DetailMovieActivity.EXTRA_TITLE, movie.name)
                    intent.putExtra(DetailMovieActivity.EXTRA_SOURCE, "Movies")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieAdapter = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieAdapter)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        this.notifyDataSetChanged()
    }
}