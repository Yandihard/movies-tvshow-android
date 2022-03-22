package com.yandihard.tvmoviesapp.submission.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity
import com.yandihard.tvmoviesapp.submission.source.local.LocalDataSource

class FakeFavoriteRepository(private val localDataSource: LocalDataSource) {

    fun getFavMovie(): LiveData<PagedList<DetailMovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavMovie(), config).build()
    }

    fun getFavTv(): LiveData<PagedList<DetailTvEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavTv(), config).build()
    }

}