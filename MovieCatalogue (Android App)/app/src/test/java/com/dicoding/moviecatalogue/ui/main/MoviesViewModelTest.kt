package com.dicoding.moviecatalogue.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.moviecatalogue.data.source.MovieRepository
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer : Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTv: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
//        val dummyMovies = Resource.success(DataDummy.generateDummyMovie())
        val dummyMovies = Resource.success(pagedListMovie)
        `when`(dummyMovies.data?.size).thenReturn(20)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getMovies("", "Movies")).thenReturn(movies)
        val moviesEntities = viewModel.getMovies("", "Movies").value?.data
        verify(movieRepository).getMovies("", "Movies")
        assertNotNull(moviesEntities)
        assertEquals(20, moviesEntities?.size)

        viewModel.getMovies("", "Movies").observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTvShow() {
//        val dummyTvShow = DataDummy.generateDummyTvShow()
        val dummyTvShow = Resource.success(pagedListTv)
        `when`(dummyTvShow.data?.size).thenReturn(20)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getTvShow("", "Tv Show")).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShow("", "Tv Show").value?.data
        verify(movieRepository).getTvShow("", "Tv Show")
        assertNotNull(tvShowEntities)
        assertEquals(20, tvShowEntities?.size)

        viewModel.getTvShow("", "Tv Show").observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}