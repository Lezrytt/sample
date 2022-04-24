package com.dicoding.moviecatalogue.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.moviecatalogue.data.source.MovieRepository
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.vo.Resource

class MoviesViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getMovies(sort: String, source: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return movieRepository.getMovies(sort, source)
    }

    fun getTvShow(sort: String, source: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return movieRepository.getTvShow(sort, source)
    }

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getAllMovies()

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = movieRepository.getAllTvShow()

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getFavoriteMovie()
    }

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        return movieRepository.getFavoriteTvShow()
    }
}