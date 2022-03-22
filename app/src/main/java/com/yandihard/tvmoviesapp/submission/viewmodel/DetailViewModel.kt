package com.yandihard.tvmoviesapp.submission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yandihard.tvmoviesapp.submission.source.CatalogRepository
import com.yandihard.tvmoviesapp.submission.source.local.*

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    private lateinit var movieId: String
    private lateinit var tvId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvshow(tvId: String) {
        this.tvId = tvId
    }

    fun getDetailMovie(): LiveData<DetailMovieEntity> = catalogRepository.getMovie(movieId)

    fun getGenreMovie(): LiveData<List<GenreEntity>> = catalogRepository.getGenreMovie(movieId)

    fun getLangMovie(): LiveData<List<LanguageEntity>> = catalogRepository.getLangMovie(movieId)

    fun getDetailTv(): LiveData<DetailTvEntity> = catalogRepository.getTvShow(tvId)

    fun getNetTv(): LiveData<List<NetworkEntity>> = catalogRepository.getNetTv(tvId)

    fun getGenreTv(): LiveData<List<GenreTvEntity>> = catalogRepository.getGenreTv(tvId)

    fun getLangTv(): LiveData<List<LanguageTvEntity>> = catalogRepository.getLangTv(tvId)
}