package com.yandihard.tvmoviesapp.submission.listener

import androidx.recyclerview.widget.DiffUtil
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity

class FavoriteTvDiffCallback(private val mOldTvList: ArrayList<DetailTvEntity?>, private val mNewTvList: List<DetailTvEntity?>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldTvList.size
    }
    override fun getNewListSize(): Int {
        return mNewTvList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldTvList[oldItemPosition]?.tvId == mNewTvList[newItemPosition]?.tvId
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldTvList[oldItemPosition]
        val newEmployee = mNewTvList[newItemPosition]
        return oldEmployee?.name == newEmployee?.name && oldEmployee?.overview == newEmployee?.overview
    }
}