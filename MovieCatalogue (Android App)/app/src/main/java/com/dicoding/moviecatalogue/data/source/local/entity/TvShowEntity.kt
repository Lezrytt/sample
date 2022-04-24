package com.dicoding.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "name")
    var name : String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false
)
