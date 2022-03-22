package com.yandihard.tvmoviesapp.submission.source

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity
import com.yandihard.tvmoviesapp.submission.source.local.LocalDataSource
import com.yandihard.tvmoviesapp.submission.source.local.room.FavoriteDao
import com.yandihard.tvmoviesapp.submission.source.local.room.FavoriteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application, private val localDataSource: LocalDataSource) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private val mFavoriteDao: FavoriteDao?

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getFavMovie(): LiveData<PagedList<DetailMovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavMovie(), config).build()
    }

    fun insertMovie(movie: DetailMovieEntity) {
        executorService.execute { mFavoriteDao?.insertMovie(movie) }
    }

    fun deleteMovie(movie: DetailMovieEntity) {
        executorService.execute { mFavoriteDao?.deleteMovie(movie) }
    }

    fun getFavTv(): LiveData<PagedList<DetailTvEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavTv(), config).build()
    }

    fun insertTv(tvshow: DetailTvEntity) {
        executorService.execute { mFavoriteDao?.insertTv(tvshow) }
    }

    fun deleteTv(tvshow: DetailTvEntity) {
        executorService.execute { mFavoriteDao?.deleteTv(tvshow) }
    }
}