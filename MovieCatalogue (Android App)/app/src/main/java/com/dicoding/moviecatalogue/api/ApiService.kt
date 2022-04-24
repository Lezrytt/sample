package com.dicoding.moviecatalogue.api

import com.dicoding.moviecatalogue.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        private const val apiKey = BuildConfig.API_KEY
    }

    @GET("3/movie/popular?api_key=$apiKey&language=en-US&page=1")
    fun getPopularMovie(
    ) : Call<TopRatedResponse>
    @GET("3/tv/popular?api_key=$apiKey&language=en-US&page=1")
    fun getPopularTvShow(
    ) : Call<PopularTvShowResponse>
    @GET("3/movie/{movie_id}?api_key=$apiKey&language=en-US")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Int
    ) : Call<DetailResponse>
    @GET("3/tv/{tv_id}?api_key=$apiKey&language=en-US")
    fun getDetailTvShow(
        @Path("tv_id") tv_id: Int
    ) : Call<DetailTvResponse>
}