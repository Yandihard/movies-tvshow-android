package com.yandihard.tvmoviesapp.submission.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.yandihard.tvmoviesapp.databinding.ActivityFavoriteBinding
import com.yandihard.tvmoviesapp.submission.adapter.SectionsPagerAdapter
import com.yandihard.tvmoviesapp.submission.adapter.SectionsPagerAdapter2nd

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = SectionsPagerAdapter2nd(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPagerFav
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabsFav
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        onRestart()
        super.onBackPressed()
    }
}