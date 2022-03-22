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
import com.yandihard.tvmoviesapp.databinding.RowToMoviesBinding
import com.yandihard.tvmoviesapp.submission.activity.DetailMovieActivity
import com.yandihard.tvmoviesapp.submission.listener.CustomOnItemClickListener
import com.yandihard.tvmoviesapp.submission.source.local.MovieEntity

class MoviesAdapter(private val activity: Activity) : PagedListAdapter<MovieEntity, MoviesAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.MovieViewHolder {
        val binding = RowToMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as MovieEntity)
    }

    inner class MovieViewHolder(private val binding: RowToMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${movie.image}")
                    .into(binding.imgItemMovies)
            binding.tvMovieOverview.text = movie.overview
            binding.tvMovieName.text = movie.name
            itemView.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                override fun onItemClicked(view: View, position: Int) {
                    val manageDetailIntent = Intent(activity, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
                    }
                    activity.startActivity(manageDetailIntent)
                }
            }))
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieEntity> = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldNote: MovieEntity, newNote: MovieEntity): Boolean {
                return oldNote.name == newNote.name && oldNote.overview == newNote.overview
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: MovieEntity, newNote: MovieEntity): Boolean {
                return oldNote == newNote
            }
        }
    }
}