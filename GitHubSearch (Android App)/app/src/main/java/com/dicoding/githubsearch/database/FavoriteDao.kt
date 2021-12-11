package com.dicoding.githubsearch.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite ORDER BY username ASC")
    fun getAllFavorite(): LiveData<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE username = :username")
    fun getSpecificUser(username: String): LiveData<List<Favorite>>
}