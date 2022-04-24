package com.dicoding.moviecatalogue.ui

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.moviecatalogue.api.DetailResponse
import com.dicoding.moviecatalogue.api.DetailTvResponse
import com.dicoding.moviecatalogue.api.ResultsItem
import com.dicoding.moviecatalogue.api.ResultsTvShowItem
import com.dicoding.moviecatalogue.data.source.MovieDataSource
import com.dicoding.moviecatalogue.data.source.NetworkBoundResource
import com.dicoding.moviecatalogue.data.source.local.LocalDataSource
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.data.source.remote.ApiResponse
import com.dicoding.moviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalogue.utils.AppExecutors
import com.dicoding.moviecatalogue.utils.SortUtils
import com.dicoding.moviecatalogue.vo.Resource

class FakeMovieRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): MovieDataSource {

    override fun getMovies(sort: String, source: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val query = SortUtils.getSortedQuery(sort, source)
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(query), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<ResultsItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id, response.title, response.overview,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTvShow(sort: String, source: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<ResultsTvShowItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val query = SortUtils.getSortedQuery(sort, source)
                val config = PagedList.Config.Builder()
                    .setPageSize(20)
                    .setInitialLoadSizeHint(20)
                    .setEnablePlaceholders(false)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShow(query), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvShowItem>>> {
                return remoteDataSource.getTvShow()
            }

            override fun saveCallResult(data: List<ResultsTvShowItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (i in data) {
                    val tvShow = TvShowEntity(i.id, i.name, i.overview, i.posterPath)
                    tvList.add(tvShow)
                }
                localDataSource.insertTvShow(tvList)
            }
        }.asLiveData()
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<ResultsItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id, response.title, response.overview,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<ResultsTvShowItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setPageSize(20)
                    .setInitialLoadSizeHint(20)
                    .setEnablePlaceholders(false)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvShowItem>>> {
                return remoteDataSource.getTvShow()
            }

            override fun saveCallResult(data: List<ResultsTvShowItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (i in data) {
                    val tvShow = TvShowEntity(i.id, i.name, i.overview, i.posterPath)
                    tvList.add(tvShow)
                }
                localDataSource.insertTvShow(tvList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getDetailMovie(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<DetailResponse>> {
                return remoteDataSource.getDetailMovie(movieId)
            }

            override fun saveCallResult(data: DetailResponse) {
                val movie = MovieEntity(data.id, data.title, data.overview, data.posterPath)
                localDataSource.updateDetailMovies(movie)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(movieId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getDetailTvShow(movieId)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse>> {
                return remoteDataSource.getDetailTvShow(movieId)
            }

            override fun saveCallResult(data: DetailTvResponse) {
                val tvShow = TvShowEntity(data.id, data.name, data.overview, data.posterPath)
                localDataSource.updateDetailTvShow(tvShow)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(),config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) {
        return appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }
    }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) {
        return appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }
    }

}