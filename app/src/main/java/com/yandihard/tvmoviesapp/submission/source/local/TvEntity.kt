package com.yandihard.tvmoviesapp.submission.source.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class TvEntity(
        @ColumnInfo(name = "overview")
        var overview: String? = null,

        @ColumnInfo(name = "image")
        var image: String? = null,

        @ColumnInfo(name = "name")
        var name: String? = null,

        @PrimaryKey
        @ColumnInfo(name = "tvId")
        var tvId: Int? = null
) : Parcelable

@Entity
@Parcelize
data class DetailTvEntity(
        @ColumnInfo(name = "name")
        var name: String? = null,

        @ColumnInfo(name = "type")
        var type: String? = null,

        @PrimaryKey
        @ColumnInfo(name = "tvId")
        var tvId: Int? = null,

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

data class NetworkEntity(
        var name: String? = null
)

data class LanguageTvEntity(
        var name: String? = null
)

data class GenreTvEntity(
        var name: String? = null
)
