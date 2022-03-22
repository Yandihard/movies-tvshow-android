package com.yandihard.tvmoviesapp.submission.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity
import com.yandihard.tvmoviesapp.submission.source.local.MovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.TvEntity

@Database(entities = [DetailMovieEntity::class, DetailTvEntity::class, MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context?): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java) {
                    INSTANCE = context?.applicationContext?.let {
                        Room.databaseBuilder(it,
                                FavoriteDatabase::class.java, "favorite_database")
                                .build()
                    }
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }
}