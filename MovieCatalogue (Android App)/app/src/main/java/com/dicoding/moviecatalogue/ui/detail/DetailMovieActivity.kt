package com.dicoding.moviecatalogue.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory
import com.dicoding.moviecatalogue.vo.Status

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var sourceActivity: String
    lateinit var id: String
    private var isFavorite: Boolean? = null
    private lateinit var viewModel: DetailViewModel
    lateinit var movieEntity: MovieEntity
    lateinit var tvShowEntity: TvShowEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_ID)
            val movieTitle = extras.getString(EXTRA_TITLE)
            val sourceExtras = extras.getString(EXTRA_SOURCE)
            id = movieId.toString()
            sourceActivity = sourceExtras.toString()
            if (sourceExtras == "Movies") {
                viewModel.getDetailMovie(movieId).observe(this, { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> binding.progressBarDetail.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBarDetail.visibility = View.GONE
                                getDetailMovie(movie.data)
                                movieEntity = movie.data!!
                                isFavorite = movie.data.isFavorite
                            }
                            Status.ERROR -> {
                                binding.progressBarDetail.visibility = View.GONE
                                Toast.makeText(this, "Error loading data!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                })
            } else {
                viewModel.getDetailTvShow(movieId).observe(this, { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> binding.progressBarDetail.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBarDetail.visibility = View.GONE
                                getDetailTvShow(tvShow.data)
                                tvShowEntity = tvShow.data!!
                                isFavorite = tvShow.data.isFavorite
                            }
                            Status.ERROR -> {
                                binding.progressBarDetail.visibility = View.GONE
                                Toast.makeText(this, "Error loading data!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                })
            }
            supportActionBar?.title = movieTitle
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite, menu)
        val iconMenu = menu?.findItem(R.id.favorite_menu)
        if (isFavorite == true) {
            iconMenu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            iconMenu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favorite_menu -> {
                if (sourceActivity == "Movies") {
                    viewModel.setMovieFavorite(movieEntity)
                    invalidateOptionsMenu()
                    Log.e("movie id: ", id)
                } else {
                    viewModel.setTvShowFavorite(tvShowEntity)
                    invalidateOptionsMenu()
                }
            }
            android.R.id.home -> {
                super.onBackPressed()
            }
        }
        isFavorite = !isFavorite!!
        return super.onOptionsItemSelected(item)
    }

    private fun getDetailMovie(movie: MovieEntity?) {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+ movie?.image)
            .into(binding.imageMovie)
        with(binding){
            tvDetailDesc.text = movie?.description
            tvDetailTitle.text = movie?.name
        }
        isFavorite = movie?.isFavorite
    }

    private fun getDetailTvShow(tvShow: TvShowEntity?) {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+ tvShow?.image)
            .into(binding.imageMovie)
        with(binding){
            tvDetailDesc.text = tvShow?.description
            tvDetailTitle.text = tvShow?.name
        }
        isFavorite = tvShow?.isFavorite
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_SOURCE = "extra_source"
    }
}