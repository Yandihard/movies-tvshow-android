package com.yandihard.tvmoviesapp.submission.source.remote.network

import com.yandihard.tvmoviesapp.submission.source.remote.response.DetailMovie
import com.yandihard.tvmoviesapp.submission.source.remote.response.DetailTv
import com.yandihard.tvmoviesapp.submission.source.remote.response.MovieResponse
import com.yandihard.tvmoviesapp.submission.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: token 2ab06a079ee4483e53c19069a9231144")
    @GET("movie/popular")
    fun getListMovies(@Query("api_key") api: String): Call<MovieResponse>

    @GET("tv/popular")
    fun getListTvshows(@Query("api_key") api: String): Call<TvResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") id: String,
                       @Query("api_key") api: String
    ): Call<DetailMovie>

    @GET("tv/{tv_id}")
    fun getDetailTv(@Path("tv_id") id: String,
                    @Query("api_key") api: String
    ): Call<DetailTv>
}