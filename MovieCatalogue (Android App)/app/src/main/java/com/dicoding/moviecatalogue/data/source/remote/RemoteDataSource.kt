package com.dicoding.moviecatalogue.data.source.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.moviecatalogue.api.*
import com.dicoding.moviecatalogue.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val context: Context) {

    companion object {
        @Volatile
        private var instances: RemoteDataSource? = null

        fun getInstance(helper: Context): RemoteDataSource =
            instances ?: synchronized(this) {
                instances ?: RemoteDataSource(helper).apply { instances = this}
            }

        private val TAG = RemoteDataSource::class.java.toString()
    }

    fun getMovies(): LiveData<ApiResponse<List<ResultsItem>>> {
        val resultMovies = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        val client = ApiConfig.getApiService().getPopularMovie()
        EspressoIdlingResources.increment()
        client.enqueue(object: Callback<TopRatedResponse> {
            override fun onResponse(
                call: Call<TopRatedResponse>,
                response: Response<TopRatedResponse>
            ) {
                val resp = response.body()?.results

                if (resp != null) {
                    resultMovies.value = ApiResponse.success(resp)
                }

//                resp?.let { callback.onAllMoviesReceived(it) }
                EspressoIdlingResources.decrement()
            }
            override fun onFailure(call: Call<TopRatedResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultMovies
    }

    fun getTvShow(): LiveData<ApiResponse<List<ResultsTvShowItem>>>{
        val resultTvShow = MutableLiveData<ApiResponse<List<ResultsTvShowItem>>>()
        val client = ApiConfig.getApiService().getPopularTvShow()
        EspressoIdlingResources.increment()
        client.enqueue(object: Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                val resp = response.body()?.results
                if (resp != null) {
                    resultTvShow.value = ApiResponse.success(resp)
                }
//                resp?.let { callback.onAllTvShowReceived(it) }
                EspressoIdlingResources.decrement()
            }
            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }

        })
        return resultTvShow
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<DetailResponse>> {
        val resultDetail = MutableLiveData<ApiResponse<DetailResponse>>()
        val client = ApiConfig.getApiService().getDetailMovie(movieId)
        EspressoIdlingResources.increment()
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                val resp = response.body()
                if (resp != null) {
                    resultDetail.value = ApiResponse.success(resp)
//                    callback.onAllMovieDetailReceived(resp)
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultDetail
    }

    fun getDetailTvShow(tvShowId: Int): LiveData<ApiResponse<DetailTvResponse>> {
        val resultDetailTvShow = MutableLiveData<ApiResponse<DetailTvResponse>>()
        val client = ApiConfig.getApiService().getDetailTvShow(tvShowId)
        EspressoIdlingResources.increment()
        client.enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                val resp = response.body()
                if (resp != null) {
                    resultDetailTvShow.value = ApiResponse.success(resp)
//                    callback.onAllTvShowDetailReceived(resp)
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }

        })
        return resultDetailTvShow
    }
}


