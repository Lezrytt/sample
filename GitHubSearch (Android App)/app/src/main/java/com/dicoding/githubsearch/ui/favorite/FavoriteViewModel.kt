<<<<<<< HEAD
package com.dicoding.githubsearch.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.repository.FavoriteRepository

class FavoriteViewModel(application: Application) : ViewModel() {

    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorites()
    fun getSpecificUser(username: String): LiveData<List<Favorite>> = mFavoriteRepository.getSpecificUser(username)
=======
package com.dicoding.githubsearch.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.repository.FavoriteRepository

class FavoriteViewModel(application: Application) : ViewModel() {

    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorites()
    fun getSpecificUser(username: String): LiveData<List<Favorite>> = mFavoriteRepository.getSpecificUser(username)
>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
}