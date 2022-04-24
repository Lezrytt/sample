package com.dicoding.moviecatalogue.api

import com.google.gson.annotations.SerializedName

data class PopularTvShowResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<ResultsTvShowItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class ResultsTvShowItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

)
