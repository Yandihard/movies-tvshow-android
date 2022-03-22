package com.yandihard.tvmoviesapp.submission.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.yandihard.tvmoviesapp.R
import com.yandihard.tvmoviesapp.databinding.ActivityDetailMovieBinding
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.MovieEntity
import com.yandihard.tvmoviesapp.submission.viewmodel.DetailViewModel
import com.yandihard.tvmoviesapp.submission.viewmodel.FavoriteViewModel
import com.yandihard.tvmoviesapp.submission.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private var statusFavorite = false

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val FAVORITE_MOVIE = "favorite_movie"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movies = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE)
        val movieEntity = intent.getParcelableExtra<DetailMovieEntity>(FAVORITE_MOVIE)
        val movieId = movies?.movieId.toString()
        val detailMovieId = movieEntity?.movieId.toString()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        if (movies != null) {
            viewModel.setSelectedMovie(movieId)
        }else{
            viewModel.setSelectedMovie(detailMovieId)
        }
        val favViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getDetailMovie().observe(this) { movie ->
            binding.progressBar.visibility = View.GONE
            populateMovie(movie)
            favoriteCheck(favViewModel, movie)
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                addToFavorite(movie, favViewModel)
            }
        }

        populateGenreMovie(viewModel)
        populateLangMovie(viewModel)
    }

    private fun favoriteCheck(favViewModel: FavoriteViewModel, movie: DetailMovieEntity) {
        favViewModel.getFavMovie().observe(this) {
            for (data in it) {
//                if (data?.movieId == movie.movieId) {
//                    statusFavorite = true
//                    setStatusFavorite(statusFavorite)
//                }
                val listMovieId: MutableList<Int> = mutableListOf()
                data?.movieId.let {
                    if (it != null) {
                        listMovieId.add(it)
                    }
                }
                if (listMovieId.contains(movie.movieId)) {
                    statusFavorite = true
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun addToFavorite(movie: DetailMovieEntity, favViewModel: FavoriteViewModel) {
        if (!statusFavorite) {
            favViewModel.insertFavMovie(movie)
            Toast.makeText(this@DetailMovieActivity, "Berhasil menambahkan favorite", Toast.LENGTH_SHORT).show()
            statusFavorite = !statusFavorite
            setStatusFavorite(statusFavorite)
        } else {
            favViewModel.deleteFavMovie(movie)
            Toast.makeText(this@DetailMovieActivity, "Berhasil menghapus favorite", Toast.LENGTH_SHORT).show()
            statusFavorite = false
            setStatusFavorite(statusFavorite)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateMovie(movie: DetailMovieEntity) {
            val movieRevenue = "$${movie.revenue.toString()}"
            val movieBudget = "$${movie.budget.toString()}"

            binding.tvDetailMovieName.text = movie.name
            binding.tvDetailMovieAired.text = movie.release
            binding.tvDetailMovieStatus.text = movie.status
            binding.tvDetailMovieRating.text = movie.score.toString().plus("%")
            if (movie.budget == 0) binding.tvDetailMovieBudget.text = "-"
            if (movie.budget != 0) binding.tvDetailMovieBudget.text = movieBudget
            if (movie.revenue == 0) binding.tvDetailMovieRev.text = "-"
            if (movie.revenue != 0) binding.tvDetailMovieRev.text = movieRevenue
            binding.tvDetailMovieOver.text = movie.overview
            Glide.with(this)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${movie.image}")
                    .into(binding.imgDetailMovie)
    }

    private fun populateGenreMovie(viewModel: DetailViewModel) {
        viewModel.getGenreMovie().observe(this) { genres ->
            if (genres != null) {
                val listGenres: MutableList<String> = mutableListOf()
                for (genre in genres) {
                    genre.name?.let { listGenres.add(it) }
                }
                binding.tvDetailMovieGenre.text = listGenres.joinToString()
            }
        }
    }

    private fun populateLangMovie(viewModel: DetailViewModel) {
        viewModel.getLangMovie().observe(this) { languages ->
            if (languages != null) {
                val listLang = ArrayList<String>()
                for (lang in languages) {
                    lang.name?.let { listLang.add(it) }
                }
                binding.tvDetailMovieLang.text = listLang.joinToString()
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_44))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_44))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(i: Int) {
        when (i) {
            R.id.fav -> {
                val aboutIntent = Intent(this, FavoriteActivity::class.java)
                startActivity(aboutIntent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onRestart() {
        finish()
        startActivity(intent)
        super.onRestart()
    }
}