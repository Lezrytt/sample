<<<<<<< HEAD
package com.dicoding.githubsearch.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.githubsearch.ui.detail.FollowerFragment
import com.dicoding.githubsearch.ui.detail.FollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity, private val UserData: String): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment.newInstance(UserData)
            1 -> fragment = FollowingFragment.newInstance(UserData)
        }
        return fragment as Fragment
    }
=======
package com.dicoding.githubsearch.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.githubsearch.ui.detail.FollowerFragment
import com.dicoding.githubsearch.ui.detail.FollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity, private val UserData: String): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment.newInstance(UserData)
            1 -> fragment = FollowingFragment.newInstance(UserData)
        }
        return fragment as Fragment
    }
>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
}