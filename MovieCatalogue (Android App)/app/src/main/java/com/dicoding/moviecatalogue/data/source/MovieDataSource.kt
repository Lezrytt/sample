package com.dicoding.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getMovies(sort: String, source: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShow(sort: String, source: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(movieId: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

}