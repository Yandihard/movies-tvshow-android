package com.yandihard.tvmoviesapp.submission.source.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class MovieEntity(
        @ColumnInfo(name = "overview")
        var overview: String? = null,

        @ColumnInfo(name = "name")
        var name: String? = null,

        @ColumnInfo(name = "image")
        var image: String? = null,

        @PrimaryKey
        @ColumnInfo(name = "movieId")
        var movieId: Int? = null
) : Parcelable

@Entity
@Parcelize
data class DetailMovieEntity(
        @ColumnInfo(name = "name")
        var name: String? = null,

        @ColumnInfo(name = "revenue")
        var revenue: Int? = null,

        @PrimaryKey
        @ColumnInfo(name = "movieId")
        var movieId: Int? = null,

        @ColumnInfo(name = "budget")
        var budget: Int? = null,

        @ColumnInfo(name = "overview")
        var overview: String? = null,

        @ColumnInfo(name = "image")
        var image: String? = null,

        @ColumnInfo(name = "release")
        var release: String? = null,

        @ColumnInfo(name = "score")
        var score: Double? = null,

        @ColumnInfo(name = "status")
        var status: String? = null
) : Parcelable

data class GenreEntity(
        var name: String? = null
)

data class LanguageEntity(
        var name: String? = null
)