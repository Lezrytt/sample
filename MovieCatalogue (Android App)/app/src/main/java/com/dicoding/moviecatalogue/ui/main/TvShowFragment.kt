package com.dicoding.moviecatalogue.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.databinding.FragmentTvShowBinding
import com.dicoding.moviecatalogue.utils.SortUtils
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory
import com.dicoding.moviecatalogue.vo.Status


class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    var sort: String? = null
    private lateinit var viewModel: MoviesViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val sortBundle = arguments?.getString(MoviesFragment.EXTRA_SORT)
            sort = sortBundle
        }
        if (activity!= null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            tvShowAdapter = TvShowAdapter()
            binding.progressBarTvshow.visibility = View.VISIBLE
            viewModel.getAllTvShow().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> binding.progressBarTvshow.visibility = View.GONE
                        Status.SUCCESS -> {
                            binding.progressBarTvshow.visibility = View.GONE
                            tvShowAdapter.setTvShow(tvShow.data)
                            tvShowAdapter.submitList(tvShow.data)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.progressBarTvshow.visibility = View.GONE
                            Toast.makeText(context, "Loading data error!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
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
        viewModel.getTvShow(sort.toString(), "Movies").observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> binding.progressBarTvshow.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBarTvshow.visibility = View.GONE
                        tvShowAdapter.setTvShow(movies.data)
                        tvShowAdapter.submitList(movies.data)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        binding.progressBarTvshow.visibility = View.GONE
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