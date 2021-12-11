<<<<<<< HEAD
package com.dicoding.githubsearch.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.repository.FavoriteRepository

class FavoriteAddUpdateViewModel(application: Application): ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(favorite: Favorite) {
        mFavoriteRepository.insert(favorite)
    }

    fun delete(favorite: Favorite) {
        mFavoriteRepository.delete(favorite)
    }

=======
package com.dicoding.githubsearch.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.repository.FavoriteRepository

class FavoriteAddUpdateViewModel(application: Application): ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(favorite: Favorite) {
        mFavoriteRepository.insert(favorite)
    }

    fun delete(favorite: Favorite) {
        mFavoriteRepository.delete(favorite)
    }

>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
}