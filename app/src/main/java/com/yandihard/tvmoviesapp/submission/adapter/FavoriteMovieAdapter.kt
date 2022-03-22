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
import com.yandihard.tvmoviesapp.databinding.RowFavMoviesBinding
import com.yandihard.tvmoviesapp.submission.activity.DetailMovieActivity
import com.yandihard.tvmoviesapp.submission.listener.CustomOnItemClickListener
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity

class FavoriteMovieAdapter(private val activity: Activity) : PagedListAdapter<DetailMovieEntity, FavoriteMovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieAdapter.MovieViewHolder {
        val binding = RowFavMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as DetailMovieEntity)
    }

    inner class MovieViewHolder(private val binding: RowFavMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DetailMovieEntity) {
            Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${movie.image}")
                    .into(binding.imgFavMovies)
            binding.tvFavMovieOverview.text = movie.overview
            binding.tvFavMovieName.text = movie.name
            itemView.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                override fun onItemClicked(view: View, position: Int) {
                    val manageDetailIntent = Intent(activity, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.FAVORITE_MOVIE, movie)
                    }
                    activity.startActivity(manageDetailIntent)
                }
            }))
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<DetailMovieEntity> = object : DiffUtil.ItemCallback<DetailMovieEntity>() {
            override fun areItemsTheSame(oldNote: DetailMovieEntity, newNote: DetailMovieEntity): Boolean {
                return oldNote.name == newNote.name && oldNote.overview == newNote.overview
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: DetailMovieEntity, newNote: DetailMovieEntity): Boolean {
                return oldNote == newNote
            }
        }
    }
}