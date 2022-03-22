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
import com.yandihard.tvmoviesapp.R
import com.yandihard.tvmoviesapp.databinding.RowToMoviesBinding
import com.yandihard.tvmoviesapp.databinding.RowToTvshowBinding
import com.yandihard.tvmoviesapp.submission.activity.DetailTvshowActivity
import com.yandihard.tvmoviesapp.submission.listener.CustomOnItemClickListener
import com.yandihard.tvmoviesapp.submission.source.local.MovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.TvEntity

class TvshowsAdapter(private val activity: Activity) : PagedListAdapter<TvEntity, TvshowsAdapter.TvshowViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowsAdapter.TvshowViewHolder {
        val binding = RowToTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvshowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvshowsAdapter.TvshowViewHolder, position: Int) {
        holder.bind(getItem(position) as TvEntity)
    }

    inner class TvshowViewHolder(private val binding: RowToTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvEntity) {
            Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${tvshow.image}")
                    .into(binding.imgItemTvshow)
            binding.tvTvshowOverview.text = tvshow.overview
            binding.tvTvshowName.text = tvshow.name
            itemView.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                override fun onItemClicked(view: View, position: Int) {
                    val manageDetailIntent = Intent(activity, DetailTvshowActivity::class.java).apply {
                        putExtra(DetailTvshowActivity.EXTRA_TVSHOW, tvshow)
                    }
                    activity.startActivity(manageDetailIntent)
                }
            }))
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvEntity> = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldNote: TvEntity, newNote: TvEntity): Boolean {
                return oldNote.name == newNote.name && oldNote.overview == newNote.overview
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: TvEntity, newNote: TvEntity): Boolean {
                return oldNote == newNote
            }
        }
    }
}