package com.yandihard.tvmoviesapp.submission.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity
import com.yandihard.tvmoviesapp.submission.source.local.MovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.TvEntity

@Dao
interface FavoriteDao {
    //Home
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListMovie(movie: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity")
    fun getListMovie(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListTv(tvshow: List<TvEntity>)

    @Query("SELECT * FROM TvEntity")
    fun getListTv(): DataSource.Factory<Int, TvEntity>

    //Favorite
    @Query("SELECT * FROM DetailMovieEntity")
    fun getMovie(): DataSource.Factory<Int, DetailMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: DetailMovieEntity)

    @Delete
    fun deleteMovie(movie: DetailMovieEntity)

    @Query("SELECT * FROM DetailTvEntity")
    fun getTvshow(): DataSource.Factory<Int, DetailTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(movie: DetailTvEntity)

    @Delete
    fun deleteTv(movie: DetailTvEntity)
}