package com.dicoding.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalogue.data.source.MovieRepository
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.utils.DataDummy
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
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getDetailMovie() {
        val testDummyMovie = Resource.success(DataDummy.generateDummyDetailResponseMovie())
        val detailMovie = MutableLiveData<Resource<MovieEntity>>()
        detailMovie.value = testDummyMovie

        `when`(testDummyMovie.data?.id?.let { movieRepository.getDetailMovie(it) }).thenReturn(detailMovie)
        val detailResponse = testDummyMovie.data?.id?.let { viewModel.getDetailMovie(it).value?.data }
        assertNotNull(detailResponse)
        testDummyMovie.data?.id?.let { verify(movieRepository).getDetailMovie(it) }
        assertEquals(testDummyMovie.data?.id, detailMovie.value?.data?.id)
        assertEquals(testDummyMovie.data?.name, detailMovie.value?.data?.name)
        assertEquals(testDummyMovie.data?.description, detailMovie.value?.data?.description)

        testDummyMovie.data?.id?.let { viewModel.getDetailMovie(it).observeForever(observer) }
        verify(observer).onChanged(testDummyMovie)
    }

    @Test
    fun getDetailTvShow() {
        val testDummyTvShow = Resource.success(DataDummy.generateDummyDetailResponseTv())
        val detailTv = MutableLiveData<Resource<TvShowEntity>>()
        detailTv.value = testDummyTvShow

        `when`(testDummyTvShow.data?.id?.let { movieRepository.getDetailTvShow(it) }).thenReturn(detailTv)
        val detailTvResponse = testDummyTvShow.data?.id?.let { viewModel.getDetailTvShow(it).value }
        assertNotNull(detailTvResponse)
        testDummyTvShow.data?.id?.let { verify(movieRepository).getDetailTvShow(it) }
        assertEquals(testDummyTvShow.data?.id, detailTv.value?.data?.id)
        assertEquals(testDummyTvShow.data?.name, detailTv.value?.data?.name)
        assertEquals(testDummyTvShow.data?.description, detailTv.value?.data?.description)

        testDummyTvShow.data?.id.let { viewModel.getDetailTvShow(it!!).observeForever(tvObserver) }
        verify(tvObserver).onChanged(testDummyTvShow)

    }
}