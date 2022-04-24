package com.dicoding.moviecatalogue.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.databinding.ActivityMainBinding
import com.dicoding.moviecatalogue.ui.favorite.FavoriteActivity
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        tabs = binding.tabs
        tabs.setupWithViewPager(viewPager)

        supportActionBar?.elevation = 0F
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite, menu)
        val favMenu = menu?.findItem(R.id.favorite_menu)
        favMenu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}