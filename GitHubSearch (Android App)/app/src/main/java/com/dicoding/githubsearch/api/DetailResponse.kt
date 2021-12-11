<<<<<<< HEAD
package com.dicoding.githubsearch.api

import com.google.gson.annotations.SerializedName

data class DetailResponse(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("followers")
    val followers: Int,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("following")
    val following: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("location")
    val location: String,

=======
package com.dicoding.githubsearch.api

import com.google.gson.annotations.SerializedName

data class DetailResponse(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("followers")
    val followers: Int,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("following")
    val following: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("location")
    val location: String,

>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
    )