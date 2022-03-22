package com.yandihard.tvmoviesapp.submission.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("status_message")
	val statusMessage: String? = null,

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("results")
	val results: ArrayList<ResultsMovies>,
)


data class ResultsMovies(

		@field:SerializedName("overview")
		val overview: String? = null,

		@field:SerializedName("title")
		val title: String? = null,

		@field:SerializedName("poster_path")
		val posterPath: String? = null,

		@field:SerializedName("id")
		val id: Int? = null,
)

data class DetailMovie(

		@field:SerializedName("title")
		val title: String? = null,

		@field:SerializedName("revenue")
		val revenue: Int? = null,

		@field:SerializedName("genres")
		val genres: List<GenresItem>? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("budget")
		val budget: Int? = null,

		@field:SerializedName("overview")
		val overview: String? = null,

		@field:SerializedName("poster_path")
		val posterPath: Any? = null,

		@field:SerializedName("spoken_languages")
		val spokenLanguages: List<SpokenLanguagesItem>? = null,

		@field:SerializedName("release_date")
		val releaseDate: String? = null,

		@field:SerializedName("vote_average")
		val voteAverage: Double? = null,

		@field:SerializedName("status")
		val status: String? = null
)

data class GenresItem(

		@field:SerializedName("name")
		var name: String? = null,
)

data class SpokenLanguagesItem(

		@field:SerializedName("name")
		var name: String? = null,
)