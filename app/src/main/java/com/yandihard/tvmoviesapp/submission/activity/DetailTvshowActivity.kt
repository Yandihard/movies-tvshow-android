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
import com.yandihard.tvmoviesapp.databinding.ActivityDetailTvshowBinding
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity
import com.yandihard.tvmoviesapp.submission.source.local.TvEntity
import com.yandihard.tvmoviesapp.submission.viewmodel.DetailViewModel
import com.yandihard.tvmoviesapp.submission.viewmodel.FavoriteViewModel
import com.yandihard.tvmoviesapp.submission.viewmodel.ViewModelFactory

class DetailTvshowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvshowBinding
    private var statusFavorite = false

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
        const val FAVORITE_TVSHOW = "favorite_tvshow"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvshowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail TV Show"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvshows = intent.getParcelableExtra<TvEntity>(EXTRA_TVSHOW)
        val tvEntity = intent.getParcelableExtra<DetailTvEntity>(FAVORITE_TVSHOW)
        val tvshowId = tvshows?.tvId.toString()
        val detailTvId = tvEntity?.tvId.toString()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        if (tvshows !=null) {
            viewModel.setSelectedTvshow(tvshowId)
        }else{
            viewModel.setSelectedTvshow(detailTvId)
        }
        val favViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getDetailTv().observe(this) { tv ->
            binding.progressBar.visibility = View.GONE
            populateTvshow(tv)
            favoriteCheck(favViewModel, tv)
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                addToFavorite(tv, favViewModel)
            }
        }

        populateNetTv(viewModel)
        populateGenreTv(viewModel)
        populateLangTv(viewModel)
    }

    private fun favoriteCheck(favViewModel: FavoriteViewModel, tv: DetailTvEntity) {
        favViewModel.getFavTv()?.observe(this) {
            for (data in it) {
                if (data?.tvId == tv.tvId) {
                    statusFavorite = true
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun addToFavorite(tv: DetailTvEntity, favViewModel: FavoriteViewModel) {
        if (!statusFavorite) {
            favViewModel.insertTv(tv)
            Toast.makeText(this@DetailTvshowActivity, "Berhasil menambahkan favorite", Toast.LENGTH_SHORT).show()
            statusFavorite = !statusFavorite
            setStatusFavorite(statusFavorite)
        } else {
            favViewModel.deleteTv(tv)
            Toast.makeText(this@DetailTvshowActivity, "Berhasil menghapus favorite", Toast.LENGTH_SHORT).show()
            statusFavorite = false
            setStatusFavorite(statusFavorite)
        }
    }

    private fun populateTvshow(tv: DetailTvEntity) {
            binding.tvDetailTvName.text = tv.name
            binding.tvDetailTvAired.text = tv.release
            binding.tvDetailTvStatus.text = tv.status
            binding.tvDetailTvType.text = tv.type
            binding.tvDetailTvOver.text = tv.overview
            binding.tvDetailTvRating.text = tv.score.toString().plus("%")
            Glide.with(this)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${tv.image}")
                    .into(binding.imgDetailTv)
    }

    private fun populateNetTv(viewModel: DetailViewModel) {
        viewModel.getNetTv().observe(this) { networks ->
            if (networks != null) {
                val listNets = ArrayList<String>()
                for (net in networks) {
                    net.name?.let { listNets.add(it) }
                }
                binding.tvDetailTvNet.text = listNets.joinToString()
            }
        }
    }

    private fun populateGenreTv(viewModel: DetailViewModel) {
        viewModel.getGenreTv().observe(this) { genres ->
            if (genres != null) {
                val listGenres = ArrayList<String>()
                for (genre in genres) {
                    genre.name?.let { listGenres.add(it) }
                }
                binding.tvDetailTvGenre.text = listGenres.joinToString()
            }
        }
    }

    private fun populateLangTv(viewModel: DetailViewModel) {
        viewModel.getLangTv().observe(this) { languages ->
            if (languages != null) {
                val listLangs = ArrayList<String>()
                for (lang in languages) {
                    lang.name?.let { listLangs.add(it) }
                }
                binding.tvDetailTvLang.text = listLangs.joinToString()
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