package com.yandihard.tvmoviesapp.submission.di

import android.app.Application
import android.content.Context
import com.yandihard.tvmoviesapp.submission.source.CatalogRepository
import com.yandihard.tvmoviesapp.submission.source.FavoriteRepository
import com.yandihard.tvmoviesapp.submission.source.local.LocalDataSource
import com.yandihard.tvmoviesapp.submission.source.local.room.FavoriteDatabase
import com.yandihard.tvmoviesapp.submission.source.remote.RemoteDataSource
import com.yandihard.tvmoviesapp.submission.source.remote.network.ApiConfig
import com.yandihard.tvmoviesapp.submission.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): CatalogRepository {

        val database = FavoriteDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.favoriteDao())
        val appExecutors = AppExecutors()

        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideRepository2nd(context: Context): FavoriteRepository {

        val database = FavoriteDatabase.getDatabase(context)

        val localDataSource = LocalDataSource.getInstance(database.favoriteDao())

        return FavoriteRepository(application = Application(), localDataSource)
    }
}