package com.dicoding.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.dicoding.moviecatalogue.databinding.ActivityFavoriteBinding
import com.google.android.material.tabs.TabLayout

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.favoriteViewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.favoriteTabs
        tabs.setupWithViewPager(viewPager)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}