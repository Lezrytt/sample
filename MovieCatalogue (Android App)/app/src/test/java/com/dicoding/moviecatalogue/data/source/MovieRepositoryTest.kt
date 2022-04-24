package com.dicoding.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.moviecatalogue.data.source.local.LocalDataSource
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalogue.ui.FakeMovieRepository
import com.dicoding.moviecatalogue.ui.LiveDataTestUtil
import com.dicoding.moviecatalogue.ui.PagedListUtil
import com.dicoding.moviecatalogue.utils.AppExecutors
import com.dicoding.moviecatalogue.utils.DataDummy
import com.dicoding.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateDummyMovieResponses()
    private val tvShowResponses = DataDummy.generateDummyTvShowResponses()
    private val movieDetail = DataDummy.generateDummyDetailResponseMovie()
    private val tvShowDetail = DataDummy.generateDummyDetailResponseTv()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movieResponses))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovie()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movieResponses))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShow() {
        val dataSourceFactory2 = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory2)
        movieRepository.getAllTvShow()
        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(tvShowResponses))
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteTvShow()
        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(tvShowResponses))
        verify(local).getFavoriteTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(movieResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummy.generateDummyDetailResponseMovie()
        `when`(local.getDetailMovie(movieDetail.id!!)).thenReturn(dummyMovie)
        movieRepository.getDetailMovie(movieDetail.id!!)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieDetail.id!!))
        verify(local, times(2)).getDetailMovie(movieDetail.id!!)
        assertNotNull(movieEntities.data)
        assertEquals(movieDetail.id, movieEntities.data?.id)
        assertEquals(movieDetail.name, movieEntities.data?.name)
        assertEquals(movieDetail.description, movieEntities.data?.description)
    }

    @Test
    fun getDetailTvShow() {
        val dummyMovie = MutableLiveData<TvShowEntity>()
        dummyMovie.value = tvShowDetail
        `when`(local.getDetailTvShow(tvShowDetail.id!!)).thenReturn(dummyMovie)
        movieRepository.getDetailTvShow(tvShowDetail.id!!)

        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getDetailTvShow(tvShowDetail.id!!))
        verify(local, times(2)).getDetailTvShow(tvShowDetail.id!!)
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowDetail.id, tvShowEntities.data?.id)
        assertEquals(tvShowDetail.name, tvShowEntities.data?.name)
        assertEquals(tvShowDetail.description, tvShowEntities.data?.description)
    }
}