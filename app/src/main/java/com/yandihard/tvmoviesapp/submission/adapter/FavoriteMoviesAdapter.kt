package com.yandihard.tvmoviesapp.submission.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yandihard.tvmoviesapp.databinding.RowFavMoviesBinding
import com.yandihard.tvmoviesapp.submission.activity.DetailMovieActivity
import com.yandihard.tvmoviesapp.submission.listener.CustomOnItemClickListener
import com.yandihard.tvmoviesapp.submission.listener.FavoriteMovieDiffCallback
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity

class FavoriteMoviesAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<FavoriteMoviesAdapter.MoviesViewHolder>() {
    private val listMovies = ArrayList<DetailMovieEntity>()

    fun setDataMovies(listMovies: List<DetailMovieEntity>) {
        val diffCallback = FavoriteMovieDiffCallback(this.listMovies, listMovies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listMovies.clear()
        this.listMovies.addAll(listMovies)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = RowFavMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    inner class MoviesViewHolder(private val binding: RowFavMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
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
}