package com.yandihard.tvmoviesapp.submission.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yandihard.tvmoviesapp.submission.source.remote.network.ApiConfig
import com.yandihard.tvmoviesapp.submission.source.remote.network.ApiService
import com.yandihard.tvmoviesapp.submission.source.remote.response.*
import com.yandihard.tvmoviesapp.submission.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val api: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private const val TAG_DETAIL = "DetailViewModel"
        private const val API_KEY = "2ab06a079ee4483e53c19069a9231144"

        fun getInstance(api: ApiService): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(api)
                }
    }

    fun getListMovies() : MutableLiveData<ApiResponse<ArrayList<ResultsMovies>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<ArrayList<ResultsMovies>>>()
        val client = ApiConfig.getApiService().getListMovies(API_KEY)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    resultMovies.value = ApiResponse.success(response.body()!!.results)
                } else {
                    Log.e("MovieViewModel", "onFailure: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MovieViewModel", "onFailure: ${t.message.toString()}")
            }
        })
        return resultMovies
    }

    fun getMovie(movieId: String, callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovie(movieId, API_KEY)
        client.enqueue(object : Callback<DetailMovie> {
            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                if (response.isSuccessful) {
                    response.body().let {
                        callback.onAllMovieReceived(it)
                    }
                } else {
                    Log.e(TAG_DETAIL, "onFailure: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                Log.e(TAG_DETAIL, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getGenreMovie(movieId: String, callback: LoadGenreMovieCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovie(movieId, API_KEY)
        client.enqueue(object : Callback<DetailMovie> {
            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                if (response.isSuccessful) {
                    response.body()?.genres.let {
                    callback.onAllGenreReceived(it)
                    }
                } else {
                    Log.e(TAG_DETAIL, "onFailure: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                Log.e(TAG_DETAIL, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getLangMovie(movieId: String, callback: LoadLangMovieCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovie(movieId, API_KEY)
        client.enqueue(object : Callback<DetailMovie> {
            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                if (response.isSuccessful) {
                    response.body()?.spokenLanguages.let {
                        callback.onAllLangReceived(it)
                    }
                } else {
                    Log.e(TAG_DETAIL, "onFailure: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                Log.e(TAG_DETAIL, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getListTv() : LiveData<ApiResponse<ArrayList<ResultsTv>>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<ArrayList<ResultsTv>>>()
        val client = ApiConfig.getApiService().getListTvshows(API_KEY)
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                EspressoIdlingResource.decrement()
                if (response.isSuccessful) {
                    resultTv.value = ApiResponse.success(response.body()!!.results)
                } else {
                    Log.e("TvshowViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e("TvshowViewModel", "onFailure: ${t.message.toString()}")
            }
        })
        return resultTv
    }

    fun getTvShow(tvId: String, callback: LoadTvshowCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTv(tvId, API_KEY)
            client.enqueue(object : Callback<DetailTv> {
                override fun onResponse(call: Call<DetailTv>, response: Response<DetailTv>) {
                    if (response.isSuccessful) {
                        response.body().let {
                            callback.onAllTvshowReceived(it)
                        }
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e(TAG_DETAIL, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DetailTv>, t: Throwable) {
                    Log.e(TAG_DETAIL, "onFailure: ${t.message.toString()}")
                }
            })
    }

    fun getNetTv(tvId: String, callback: LoadNetTvCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTv(tvId, API_KEY)
        client.enqueue(object : Callback<DetailTv> {
            override fun onResponse(call: Call<DetailTv>, response: Response<DetailTv>) {
                if (response.isSuccessful) {
                    response.body()?.networks.let {
                        callback.onAllNetTvReceived(it)
                    }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG_DETAIL, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailTv>, t: Throwable) {
                Log.e(TAG_DETAIL, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getGenreTv(tvId: String, callback: LoadGenreTvCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTv(tvId, API_KEY)
        client.enqueue(object : Callback<DetailTv> {
            override fun onResponse(call: Call<DetailTv>, response: Response<DetailTv>) {
                if (response.isSuccessful) {
                    response.body()?.genres.let {
                        callback.onAllGenreTvReceived(it)
                    }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG_DETAIL, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailTv>, t: Throwable) {
                Log.e(TAG_DETAIL, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getLangTv(tvId: String, callback: LoadLangTvCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTv(tvId, API_KEY)
        client.enqueue(object : Callback<DetailTv> {
            override fun onResponse(call: Call<DetailTv>, response: Response<DetailTv>) {
                if (response.isSuccessful) {
                    response.body()?.spokenLanguages.let {
                        callback.onAllLangTvReceived(it)
                    }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG_DETAIL, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailTv>, t: Throwable) {
                Log.e(TAG_DETAIL, "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(responseMovies: List<ResultsMovies>)
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(responseMovie: DetailMovie?)
    }

    interface LoadGenreMovieCallback {
        fun onAllGenreReceived(responseMovie: List<GenresItem>?)
    }

    interface LoadLangMovieCallback {
        fun onAllLangReceived(responseMovie: List<SpokenLanguagesItem>?)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(responseTv: ArrayList<ResultsTv>?)
    }

    interface LoadTvshowCallback {
        fun onAllTvshowReceived(responseTv: DetailTv?)
    }

    interface LoadNetTvCallback {
        fun onAllNetTvReceived(responseTv: List<NetworksItem>?)
    }

    interface LoadGenreTvCallback {
        fun onAllGenreTvReceived(responseTv: List<GenreItem>?)
    }

    interface LoadLangTvCallback {
        fun onAllLangTvReceived(responseTv: List<LanguagesItem>?)
    }
}