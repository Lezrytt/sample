package com.dicoding.moviecatalogue.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalogue.data.source.MovieRepository
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.vo.Resource

class DetailViewModel (private val movieRepository: MovieRepository): ViewModel() {

    val id = MutableLiveData<Int>()

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return movieRepository.getDetailMovie(movieId)
    }

    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return movieRepository.getDetailTvShow(tvShowId)
    }

    val movie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(id) { movieId ->
        movieRepository.getDetailMovie(movieId)
    }

    fun setMovieFavorite(movie: MovieEntity) {
        movieRepository.setMovieFavorite(movie, !movie.isFavorite!!)
        Log.e("movie: ", movie.name.toString())
        Log.e("movie: ", movie.isFavorite.toString())
    }

    fun setTvShowFavorite(tvShow: TvShowEntity) {
        movieRepository.setTvShowFavorite(tvShow, !tvShow.isFavorite!!)
    }

}