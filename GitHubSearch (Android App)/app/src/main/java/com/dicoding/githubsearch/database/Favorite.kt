<<<<<<< HEAD
package com.dicoding.githubsearch.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "username")
    var username: String = "",

    @ColumnInfo(name = "avatar")
    var avatar: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false

): Parcelable
=======
package com.dicoding.githubsearch.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "username")
    var username: String = "",

    @ColumnInfo(name = "avatar")
    var avatar: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false

): Parcelable
>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
