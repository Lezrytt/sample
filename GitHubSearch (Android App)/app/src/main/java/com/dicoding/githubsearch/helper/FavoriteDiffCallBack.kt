<<<<<<< HEAD
package com.dicoding.githubsearch.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.githubsearch.database.Favorite

class FavoriteDiffCallBack(private val mOldFavoriteList: List<Favorite>, private val mNewFavoriteList: List<Favorite>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteList[oldItemPosition].username == mNewFavoriteList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldFavoriteList[oldItemPosition]
        val newEmployee = mNewFavoriteList[newItemPosition]
        return oldEmployee.username == newEmployee.username && oldEmployee.avatar == newEmployee.avatar && oldEmployee.isFavorite == newEmployee.isFavorite
    }

=======
package com.dicoding.githubsearch.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.githubsearch.database.Favorite

class FavoriteDiffCallBack(private val mOldFavoriteList: List<Favorite>, private val mNewFavoriteList: List<Favorite>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteList[oldItemPosition].username == mNewFavoriteList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldFavoriteList[oldItemPosition]
        val newEmployee = mNewFavoriteList[newItemPosition]
        return oldEmployee.username == newEmployee.username && oldEmployee.avatar == newEmployee.avatar && oldEmployee.isFavorite == newEmployee.isFavorite
    }

>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
}