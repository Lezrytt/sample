<<<<<<< HEAD
package com.dicoding.githubsearch.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.database.FavoriteDao
import com.dicoding.githubsearch.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoritesDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoritesDao = db.favoriteDao()
    }

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoritesDao.getAllFavorite()
    fun getSpecificUser(username: String): LiveData<List<Favorite>> = mFavoritesDao.getSpecificUser(username)

    fun insert(favorite: Favorite) {
        executorService.execute {
            mFavoritesDao.insert(favorite)
        }
    }

    fun delete(favorite: Favorite) {
        executorService.execute {
            mFavoritesDao.delete(favorite)
        }
    }

=======
package com.dicoding.githubsearch.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.database.FavoriteDao
import com.dicoding.githubsearch.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoritesDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoritesDao = db.favoriteDao()
    }

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoritesDao.getAllFavorite()
    fun getSpecificUser(username: String): LiveData<List<Favorite>> = mFavoritesDao.getSpecificUser(username)

    fun insert(favorite: Favorite) {
        executorService.execute {
            mFavoritesDao.insert(favorite)
        }
    }

    fun delete(favorite: Favorite) {
        executorService.execute {
            mFavoritesDao.delete(favorite)
        }
    }

>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
}