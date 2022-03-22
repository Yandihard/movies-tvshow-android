package com.yandihard.tvmoviesapp.submission.listener

import androidx.recyclerview.widget.DiffUtil
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity

class FavoriteMovieDiffCallback(private val mOldMovieList: List<DetailMovieEntity>, private val mNewMovieList: List<DetailMovieEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldMovieList.size
    }
    override fun getNewListSize(): Int {
        return mNewMovieList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldMovieList[oldItemPosition].movieId == mNewMovieList[newItemPosition].movieId
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldMovieList[oldItemPosition]
        val newEmployee = mNewMovieList[newItemPosition]
        return oldEmployee.name == newEmployee.name && oldEmployee.overview == newEmployee.overview
    }
}