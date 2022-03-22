package com.yandihard.tvmoviesapp.submission.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(

		@field:SerializedName("status_message")
		val statusMessage: String? = null,

		@field:SerializedName("status_code")
		val statusCode: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("results")
	val results: ArrayList<ResultsTv>
)


data class ResultsTv(

	@field:SerializedName("overview")
    var overview: String? = null,

	@field:SerializedName("poster_path")
	var posterPath: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("id")
	var id: Int? = null,
)

data class DetailTv(

		@field:SerializedName("networks")
		val networks: List<NetworksItem>? = null,

		@field:SerializedName("type")
		var type: String? = null,

		@field:SerializedName("genres")
		val genres: List<GenreItem>? = null,

		@field:SerializedName("id")
		var id: Int? = null,

		@field:SerializedName("first_air_date")
		var firstAirDate: String? = null,

		@field:SerializedName("overview")
		var overview: String? = null,

		@field:SerializedName("poster_path")
		var posterPath: String? = null,

		@field:SerializedName("spoken_languages")
		val spokenLanguages: List<LanguagesItem>? = null,

		@field:SerializedName("vote_average")
		var voteAverage: Double? = null,

		@field:SerializedName("name")
		var name: String? = null,

		@field:SerializedName("status")
		var status: String? = null
)

data class NetworksItem(

		@field:SerializedName("name")
		var name: String? = null,
)

data class LanguagesItem(

		@field:SerializedName("name")
		var name: String? = null,
)

data class GenreItem(

		@field:SerializedName("name")
		var name: String? = null,
)
