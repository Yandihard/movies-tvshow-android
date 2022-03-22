package com.yandihard.tvmoviesapp.submission.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yandihard.tvmoviesapp.databinding.RowFavTvshowBinding
import com.yandihard.tvmoviesapp.submission.activity.DetailTvshowActivity
import com.yandihard.tvmoviesapp.submission.listener.CustomOnItemClickListener
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity

class FavoriteTvAdapter(private val activity: Activity) : PagedListAdapter<DetailTvEntity, FavoriteTvAdapter.TvViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvAdapter.TvViewHolder {
        val binding = RowFavTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteTvAdapter.TvViewHolder, position: Int) {
        holder.bind(getItem(position) as DetailTvEntity)
    }

    inner class TvViewHolder(private val binding: RowFavTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: DetailTvEntity) {
            Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${tvshow.image}")
                    .into(binding.imgFavTv)
            binding.tvFavTvOverview.text = tvshow.overview
            binding.tvFavTvName.text = tvshow.name
            itemView.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                override fun onItemClicked(view: View, position: Int) {
                    val manageDetailIntent = Intent(activity, DetailTvshowActivity::class.java).apply {
                        putExtra(DetailTvshowActivity.FAVORITE_TVSHOW, tvshow)
                    }
                    activity.startActivity(manageDetailIntent)
                }
            }))
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<DetailTvEntity> = object : DiffUtil.ItemCallback<DetailTvEntity>() {
            override fun areItemsTheSame(oldNote: DetailTvEntity, newNote: DetailTvEntity): Boolean {
                return oldNote.name == newNote.name && oldNote.overview == newNote.overview
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: DetailTvEntity, newNote: DetailTvEntity): Boolean {
                return oldNote == newNote
            }
        }
    }
}