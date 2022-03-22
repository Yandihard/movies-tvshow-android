package com.yandihard.tvmoviesapp.submission.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yandihard.tvmoviesapp.submission.source.local.*
import com.yandihard.tvmoviesapp.submission.source.remote.ApiResponse
import com.yandihard.tvmoviesapp.submission.source.remote.RemoteDataSource
import com.yandihard.tvmoviesapp.submission.source.remote.response.*
import com.yandihard.tvmoviesapp.submission.utils.AppExecutors
import com.yandihard.tvmoviesapp.submission.vo.Resource

class CatalogRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors)
    : CatalogDataSource {

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): CatalogRepository =
                instance ?: synchronized(this) {
                    instance ?: CatalogRepository(remoteData, localData, appExecutors).apply {
                        instance = this
                    }
                }
    }

    override fun getListMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, ArrayList<ResultsMovies>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getListMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<ArrayList<ResultsMovies>>> =
                    remoteDataSource.getListMovies()

            public override fun saveCallResult(data: ArrayList<ResultsMovies>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(response.overview,
                            response.title,
                            response.posterPath,
                            response.id)
                    movieList.add(movie)
                    }

                    localDataSource.insertListMovie(movieList)
                }
            }.asLiveData()
    }

    override fun getListTvshows(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, ArrayList<ResultsTv>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getListTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<ArrayList<ResultsTv>>> =
                    remoteDataSource.getListTv()

            public override fun saveCallResult(data: ArrayList<ResultsTv>) {
                val tvList = ArrayList<TvEntity>()
                        for (response in data) {
                            val tvshow = TvEntity(response.overview,
                                    response.posterPath,
                                    response.name,
                                    response.id)
                            tvList.add(tvshow)
                        }

                        localDataSource.insertListTv(tvList)
                    }
                }.asLiveData()
    }

    override fun getMovie(movieId: String): LiveData<DetailMovieEntity> {
        val movieResult = MutableLiveData<DetailMovieEntity>()
        remoteDataSource.getMovie(movieId, object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(responseMovie: DetailMovie?) {
                lateinit var movie: DetailMovieEntity
                responseMovie.let {
                    if (it?.id.toString() == movieId) {
                        if (it != null) {
                            movie = DetailMovieEntity(
                                    it.title,
                                    it.revenue,
                                    it.id,
                                    it.budget,
                                    it.overview,
                                    it.posterPath.toString(),
                                    it.releaseDate,
                                    it.voteAverage,
                                    it.status)
                            movieResult.postValue(movie)
                        }
                    }
                }
            }
        })
        return movieResult
    }

    override fun getGenreMovie(movieId: String): LiveData<List<GenreEntity>> {
        val genresResults = MutableLiveData<List<GenreEntity>>()
        remoteDataSource.getGenreMovie(movieId, object : RemoteDataSource.LoadGenreMovieCallback {
            override fun onAllGenreReceived(responseMovie: List<GenresItem>?) {
                val genreList = ArrayList<GenreEntity>()
                if (responseMovie != null) {
                    for (response in responseMovie) {
                        val genre = GenreEntity(response.name)
                        genreList.add(genre)
                        genresResults.postValue(genreList)
                    }
                }
            }
        })
        return genresResults
    }

    override fun getLangMovie(movieId: String): LiveData<List<LanguageEntity>> {
        val langResults = MutableLiveData<List<LanguageEntity>>()
        remoteDataSource.getLangMovie(movieId, object : RemoteDataSource.LoadLangMovieCallback {
            override fun onAllLangReceived(responseMovie: List<SpokenLanguagesItem>?) {
                val langList = ArrayList<LanguageEntity>()
                if (responseMovie != null) {
                    for (response in responseMovie) {
                        val language = LanguageEntity(response.name)
                        langList.add(language)
                        langResults.postValue(langList)
                    }
                }
            }
        })
        return langResults
    }

    override fun getTvShow(tvId: String): LiveData<DetailTvEntity> {
        val tvResults = MutableLiveData<DetailTvEntity>()
        remoteDataSource.getTvShow(tvId, object : RemoteDataSource.LoadTvshowCallback {
            override fun onAllTvshowReceived(responseTv: DetailTv?) {
                lateinit var tvshow: DetailTvEntity
                responseTv.let {
                    if (it?.id.toString() == tvId) {
                        if (it != null) {
                            tvshow = DetailTvEntity(
                                    it.name,
                                    it.type,
                                    it.id,
                                    it.overview,
                                    it.posterPath,
                                    it.firstAirDate,
                                    it.voteAverage,
                                    it.status)
                            tvResults.postValue(tvshow)
                        }
                    }
                }
            }

        })
        return tvResults
    }

    override fun getNetTv(tvId: String): LiveData<List<NetworkEntity>> {
        val netResults = MutableLiveData<List<NetworkEntity>>()
        remoteDataSource.getNetTv(tvId, object : RemoteDataSource.LoadNetTvCallback {
            override fun onAllNetTvReceived(responseTv: List<NetworksItem>?) {
                val netList = ArrayList<NetworkEntity>()
                if (responseTv != null) {
                    for (response in responseTv) {
                        val network = NetworkEntity(response.name)
                        netList.add(network)
                        netResults.postValue(netList)
                    }
                }
            }
        })
        return netResults
    }

    override fun getGenreTv(tvId: String): LiveData<List<GenreTvEntity>> {
        val genreResults = MutableLiveData<List<GenreTvEntity>>()
        remoteDataSource.getGenreTv(tvId, object : RemoteDataSource.LoadGenreTvCallback {
            override fun onAllGenreTvReceived(responseTv: List<GenreItem>?) {
                val genreList = ArrayList<GenreTvEntity>()
                if (responseTv != null) {
                    for (response in responseTv) {
                        val genre = GenreTvEntity(response.name)
                        genreList.add(genre)
                        genreResults.postValue(genreList)
                    }
                }
            }
        })
        return genreResults
    }

    override fun getLangTv(tvId: String): LiveData<List<LanguageTvEntity>> {
        val langResults = MutableLiveData<List<LanguageTvEntity>>()
        remoteDataSource.getLangTv(tvId, object : RemoteDataSource.LoadLangTvCallback {
            override fun onAllLangTvReceived(responseTv: List<LanguagesItem>?) {
                val langList = ArrayList<LanguageTvEntity>()
                if (responseTv != null) {
                    for (response in responseTv) {
                        val language = LanguageTvEntity(response.name)
                        langList.add(language)
                        langResults.postValue(langList)
                    }
                }
            }
        })
        return langResults
    }
}