package com.dicoding.moviecatalogue.data.source.local

import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getMovies(sort: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovies(sort)
    fun getTvShow(sort: SupportSQLiteQuery): DataSource.Factory<Int, TvShowEntity> = mMovieDao.getTvShow(sort)

    fun getAllMovies():  DataSource.Factory<Int, MovieEntity> = mMovieDao.getAllMovies()
    fun getAllTvShow():  DataSource.Factory<Int, TvShowEntity> = mMovieDao.getAllTvShow()

    fun insertMovies(movieList: List<MovieEntity>) = mMovieDao.insertMovies(movieList)
    fun insertTvShow(tvList: List<TvShowEntity>) = mMovieDao.insertTvShow(tvList)
    fun getDetailMovie(movieId: Int) = mMovieDao.getDetailMovie(movieId)
    fun getDetailTvShow(id: Int) = mMovieDao.getDetailTvShow(id)
    fun updateDetailMovies(movie: MovieEntity) = mMovieDao.updateDetailMovie(movie)
    fun updateDetailTvShow(tvShow: TvShowEntity) = mMovieDao.updateDetailTvShow(tvShow)
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getFavoriteMovie()
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = mMovieDao.getFavoriteTvShow()

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieDao.updateDetailMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mMovieDao.updateDetailTvShow(tvShow)
    }
}