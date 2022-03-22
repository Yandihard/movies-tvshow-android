package com.yandihard.tvmoviesapp.submission.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.yandihard.tvmoviesapp.submission.source.local.*
import com.yandihard.tvmoviesapp.submission.vo.Resource

interface CatalogDataSource {

    //List Home
    fun getListMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getListTvshows(): LiveData<Resource<PagedList<TvEntity>>>

    //Detail Movie
    fun getMovie(movieId: String): LiveData<DetailMovieEntity>

    fun getGenreMovie(movieId: String): LiveData<List<GenreEntity>>

    fun getLangMovie(movieId: String): LiveData<List<LanguageEntity>>

    //Detail Tv
    fun getTvShow(tvId: String): LiveData<DetailTvEntity>
    
    fun getNetTv(tvId: String): LiveData<List<NetworkEntity>>

    fun getGenreTv(tvId: String): LiveData<List<GenreTvEntity>>

    fun getLangTv(tvId: String): LiveData<List<LanguageTvEntity>>
}