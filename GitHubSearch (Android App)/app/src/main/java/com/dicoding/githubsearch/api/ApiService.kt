package com.dicoding.githubsearch.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users?")
    fun getSearch(
        @Query("q") login: String
    ): Call<SearchResponse>
    @GET("users/{login}")
    fun getDetail(
        @Path("login") login: String
    ) : Call<DetailResponse>
    @GET("users/{login}/following")
    fun getFollowing(
        @Path("login") login: String
    ) : Call<List<FollowingResponseItem>>
    @GET("users/{login}/followers")
    fun getFollowers(
        @Path("login") login: String
    ) : Call<List<FollowersResponseItem>>
}