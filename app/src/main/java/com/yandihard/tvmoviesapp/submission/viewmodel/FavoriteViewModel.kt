package com.yandihard.tvmoviesapp.submission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yandihard.tvmoviesapp.submission.source.FavoriteRepository
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity

class FavoriteViewModel(private val mFavoriteRepository: FavoriteRepository) : ViewModel() {


    fun insertFavMovie(movie: DetailMovieEntity) {
        mFavoriteRepository.insertMovie(movie)
    }

    fun deleteFavMovie(movie: DetailMovieEntity) {
        mFavoriteRepository.deleteMovie(movie)
    }

    fun getFavMovie(): LiveData<PagedList<DetailMovieEntity>> = mFavoriteRepository.getFavMovie()

    fun getFavTv(): LiveData<PagedList<DetailTvEntity>> = mFavoriteRepository.getFavTv()

    fun insertTv(tvshow: DetailTvEntity) {
        mFavoriteRepository.insertTv(tvshow)
    }

    fun deleteTv(tvshow: DetailTvEntity) {
        mFavoriteRepository.deleteTv(tvshow)
    }
}