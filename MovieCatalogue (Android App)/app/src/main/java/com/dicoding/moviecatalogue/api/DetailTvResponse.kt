package com.dicoding.moviecatalogue.api

import com.google.gson.annotations.SerializedName

data class DetailTvResponse(


	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

)
