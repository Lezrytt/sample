package com.dicoding.moviecatalogue.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.databinding.ItemMoviesBinding
import com.dicoding.moviecatalogue.ui.detail.DetailMovieActivity

class TvShowAdapter: PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var listTvShow = ArrayList<TvShowEntity>()

    class TvShowViewHolder(private val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (tvShowEntity: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShowEntity.name
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + tvShowEntity.image)
                    .into(imgMovie)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_ID, tvShowEntity.id)
                    intent.putExtra(DetailMovieActivity.EXTRA_TITLE, tvShowEntity.name)
                    intent.putExtra(DetailMovieActivity.EXTRA_SOURCE, "TV Shows")
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvAdapter = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvAdapter)
    }

    override fun getItemCount(): Int {
        return listTvShow.size
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    fun setTvShow(tvShow: List<TvShowEntity>?) {
        if (tvShow == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShow)
        this.notifyDataSetChanged()
    }
}