package com.dicoding.moviecatalogue.di

import android.content.Context
import com.dicoding.moviecatalogue.data.source.MovieRepository
import com.dicoding.moviecatalogue.data.source.local.LocalDataSource
import com.dicoding.moviecatalogue.data.source.local.room.MovieDatabase
import com.dicoding.moviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance(context)

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}