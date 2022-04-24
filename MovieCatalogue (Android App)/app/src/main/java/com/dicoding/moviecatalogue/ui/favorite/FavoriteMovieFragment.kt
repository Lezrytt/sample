package com.dicoding.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.dicoding.moviecatalogue.ui.main.MovieAdapter
import com.dicoding.moviecatalogue.ui.main.MoviesViewModel
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            val moviesAdapter = MovieAdapter()

            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    moviesAdapter.setMovies(movies)
                }
            })
            with(binding.rvFavoriteMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }
}