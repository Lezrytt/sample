package com.dicoding.moviecatalogue.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.databinding.FragmentMoviesBinding
import com.dicoding.moviecatalogue.utils.SortUtils
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory
import com.dicoding.moviecatalogue.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MovieAdapter

    private var sort: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        Log.e("Movie1: ", sort.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val sortBundle = arguments?.getString(EXTRA_SORT)
            sort = sortBundle
        }
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            binding.progressBar.visibility = View.VISIBLE

            moviesAdapter = MovieAdapter()
            viewModel.getAllMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            moviesAdapter.setMovies(movies.data)
                            moviesAdapter.submitList(movies.data)
                            moviesAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Loading data error!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                Log.e("Movie: ", sort.toString())
            })

            with(binding.rvMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_newest -> sort = SortUtils.NEWEST
            R.id.action_oldest -> sort = SortUtils.OLDEST
            R.id.action_random -> sort = SortUtils.RANDOM
        }
        viewModel.getMovies(sort.toString(), "Movies").observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        moviesAdapter.setMovies(movies.data)
                        moviesAdapter.submitList(movies.data)
                        moviesAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Loading data error!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var EXTRA_SORT = "extra_sort"
    }
}