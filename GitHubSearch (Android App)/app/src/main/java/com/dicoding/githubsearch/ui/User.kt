package com.dicoding.githubsearch.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var User: String? = null,
    var Photo: String? = null,
    var isFavorite: Boolean? = null
): Parcelable
