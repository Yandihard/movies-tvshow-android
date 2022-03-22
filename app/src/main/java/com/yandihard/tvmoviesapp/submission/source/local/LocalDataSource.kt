package com.yandihard.tvmoviesapp.submission.source.local

import androidx.paging.DataSource
import com.yandihard.tvmoviesapp.submission.source.local.room.FavoriteDao

class LocalDataSource private constructor(private val mFavoriteDao: FavoriteDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource =
                INSTANCE ?: LocalDataSource(favoriteDao)
    }

    //Movie
    fun getListMovie(): DataSource.Factory<Int, MovieEntity> = mFavoriteDao.getListMovie()

    fun insertListMovie(movie: List<MovieEntity>) = mFavoriteDao.insertListMovie(movie)

    fun getFavMovie(): DataSource.Factory<Int, DetailMovieEntity> = mFavoriteDao.getMovie()

    //Tvshow
    fun getListTv(): DataSource.Factory<Int, TvEntity> = mFavoriteDao.getListTv()

    fun insertListTv(tvshow: List<TvEntity>) = mFavoriteDao.insertListTv(tvshow)

    fun getFavTv(): DataSource.Factory<Int, DetailTvEntity> = mFavoriteDao.getTvshow()
}