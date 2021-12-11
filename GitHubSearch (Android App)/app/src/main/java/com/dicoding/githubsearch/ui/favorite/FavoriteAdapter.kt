<<<<<<< HEAD
package com.dicoding.githubsearch.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubsearch.ui.detail.DetailActivity
import com.dicoding.githubsearch.ui.User
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.databinding.ItemRowUserBinding
import com.dicoding.githubsearch.helper.FavoriteDiffCallBack

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val listFavorites = ArrayList<Favorite>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class FavoriteViewHolder(private val binding: ItemRowUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite:Favorite) {
            binding.tvItem.text = favorite.username
            Glide.with(itemView.context)
                .load(favorite.avatar)
                .into(binding.imgPhoto)
            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                val user = User(favorite.username, favorite.avatar)
                intent.putExtra(DetailActivity.EXTRA_FAVORITE, favorite)
                intent.putExtra(DetailActivity.EXTRA_USER, user)
                it.context.startActivity(intent)
            }
        }
    }

    fun setListFavorites(listFavorites: List<Favorite>) {
        val diffCallBack = FavoriteDiffCallBack(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int {
        return listFavorites.size
    }
=======
package com.dicoding.githubsearch.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubsearch.ui.detail.DetailActivity
import com.dicoding.githubsearch.ui.User
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.databinding.ItemRowUserBinding
import com.dicoding.githubsearch.helper.FavoriteDiffCallBack

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val listFavorites = ArrayList<Favorite>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class FavoriteViewHolder(private val binding: ItemRowUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite:Favorite) {
            binding.tvItem.text = favorite.username
            Glide.with(itemView.context)
                .load(favorite.avatar)
                .into(binding.imgPhoto)
            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                val user = User(favorite.username, favorite.avatar)
                intent.putExtra(DetailActivity.EXTRA_FAVORITE, favorite)
                intent.putExtra(DetailActivity.EXTRA_USER, user)
                it.context.startActivity(intent)
            }
        }
    }

    fun setListFavorites(listFavorites: List<Favorite>) {
        val diffCallBack = FavoriteDiffCallBack(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int {
        return listFavorites.size
    }
>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
}