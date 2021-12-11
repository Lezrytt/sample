package com.dicoding.githubsearch.ui.detail


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.githubsearch.R
import com.dicoding.githubsearch.ui.adapter.SectionsPagerAdapter
import com.dicoding.githubsearch.ui.User
import com.dicoding.githubsearch.api.DetailResponse
import com.dicoding.githubsearch.api.FollowingResponseItem
import com.dicoding.githubsearch.database.Favorite
import com.dicoding.githubsearch.databinding.ActivityDetailBinding
import com.dicoding.githubsearch.ui.insert.FavoriteAddUpdateViewModel
import com.dicoding.githubsearch.ui.favorite.FavoriteViewModel
import com.dicoding.githubsearch.ui.favorite.ViewModelFactory2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var favoriteAddUpdateViewModel: FavoriteAddUpdateViewModel

    private var favorite: Favorite? = Favorite()

    private val listFavorites = ArrayList<Favorite>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        val actionBar = supportActionBar
        val sectionsPagerAdapter = SectionsPagerAdapter(this, user.User.toString())

        val favoriteViewModel = obtainViewModelList(this@DetailActivity)

        favoriteViewModel.getSpecificUser(user.User.toString()).observe(this, { favoriteList ->
            if (favoriteList != null) {
                setListFavorites(favoriteList)
            }
        })

        actionBar?.title = getString(R.string.userInformation)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        user.User?.let { detailViewModel.findUserDetail(it) }

        detailViewModel.detailUser.observe(this, {
            detailUser -> setDetailData(detailUser)
        })

        detailViewModel.listFollowing.observe(this, {
            listFollowing -> setFollowingData(listFollowing)
        })

        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        detailViewModel.snackbarText.observe(this, {
            Snackbar.make(
                window.decorView.rootView,
                it,
                Snackbar.LENGTH_LONG
            ).show()
        })

        binding.tvUserName.text = user.User
        Glide.with(this@DetailActivity)
            .load(user.Photo)
            .into(binding.imgDetailPhoto)

        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        actionBar?.elevation = 0f

        favoriteAddUpdateViewModel = obtainViewModel(this@DetailActivity)

    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setListFavorites(listFavorites: List<Favorite>) {
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)

        val favButton = menu.findItem(R.id.favoritesMenu)

        fun setStatusFavorite(statusFavorite: Boolean?) {
            if (statusFavorite == true) {
                favButton.setIcon(R.drawable.ic_baseline_favorite_24)
            } else {
                favButton.setIcon(R.drawable.ic_baseline_favorite_border_24)
            }
        }
        if (listFavorites.size != 0) {
            setStatusFavorite(listFavorites[0].isFavorite)
        } else {
            setStatusFavorite(favorite?.isFavorite)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        favorite = intent.getParcelableExtra(EXTRA_FAVORITE)

        val getFavorite: Favorite?

        if (listFavorites.size == 0) {
            getFavorite = Favorite(user.User!!,user.Photo,user.isFavorite)
        } else {
            getFavorite = listFavorites[0]
        }

        val avatar = user.Photo

        val isFavorite: Boolean = getFavorite.isFavorite == true

        when (item.itemId) {
            R.id.favoritesMenu -> {
                if (!isFavorite) {
                    favorite.let { favorite ->
                        favorite?.username = user.User!!
                        favorite?.avatar = avatar
                        favorite?.isFavorite = true
                    }
                    getFavorite.isFavorite = true
                    invalidateOptionsMenu()
                    Toast.makeText(this, getString(R.string.successAddDB), Toast.LENGTH_SHORT).show()
                    favoriteAddUpdateViewModel.insert(favorite as Favorite)
                } else if (isFavorite) {
                    favorite.let { favorite ->
                        favorite?.username = user.User!!
                        favorite?.avatar = avatar
                        favorite?.isFavorite = false
                    }
                    getFavorite.isFavorite = false
                    invalidateOptionsMenu()
                    Toast.makeText(this, getString(R.string.successDeleteDB), Toast.LENGTH_SHORT).show()
                    favoriteAddUpdateViewModel.delete(favorite as Favorite)
                }
                return true
            }
            else -> return true
        }
    }

    private fun setDetailData(detailUser: DetailResponse) {
        binding.tvFollowing.text = detailUser.following.toString()
        binding.tvFollowers.text = detailUser.followers.toString()

        if (detailUser.name == null) {
            binding.tvFullName.text = detailUser.login
        } else {
            binding.tvFullName.text = detailUser.name
        }

        binding.tvCompany.text = detailUser.company

        val mFollowingFragment = FollowingFragment()
        val mFollowersFragment = FollowerFragment()
        val mBundle = Bundle()
        val mBundle2 = Bundle()
        mBundle.putString(FollowerFragment.EXTRA_USER, detailUser.login)
        mBundle2.putString(FollowingFragment.EXTRA_USER, detailUser.login)
        mFollowingFragment.arguments = mBundle
        mFollowersFragment.arguments = mBundle2

        Log.e(ContentValues.TAG, "showSelectedUser: ${detailUser.login}")
    }

    private fun setFollowingData(listFollowing: List<FollowingResponseItem>) {
        val listUser: ArrayList<User> = ArrayList()
        for (user in listFollowing) {
            val userList = User(user.login, user.avatarUrl)
            listUser.add(userList)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteAddUpdateViewModel {
        val factory = ViewModelFactory2.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteAddUpdateViewModel::class.java]
    }

    private fun obtainViewModelList(activity: AppCompatActivity): FavoriteViewModel {
        val factory = ViewModelFactory2.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FAVORITE = "extra_favorite"
    }
}