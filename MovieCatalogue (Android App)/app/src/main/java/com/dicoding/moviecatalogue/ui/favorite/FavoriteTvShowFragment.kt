package com.dicoding.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.dicoding.moviecatalogue.ui.main.MoviesViewModel
import com.dicoding.moviecatalogue.ui.main.TvShowAdapter
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory


class FavoriteTvShowFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    tvShowAdapter.setTvShow(tvShow)
                }
            })
            with(binding.rvFavoriteTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}