package com.yandihard.tvmoviesapp.submission.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandihard.tvmoviesapp.databinding.FragmentMoviesBinding
import com.yandihard.tvmoviesapp.submission.adapter.MoviesAdapter
import com.yandihard.tvmoviesapp.submission.viewmodel.MoviesViewModel
import com.yandihard.tvmoviesapp.submission.viewmodel.ViewModelFactory
import com.yandihard.tvmoviesapp.submission.vo.Status

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter(requireActivity())
        showRecyclerList()
        binding.rvMovies.setHasFixedSize(true)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

        viewModel.getMovies().observe(this, { listMovies ->
            if (listMovies != null) {
                when (listMovies.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(listMovies.data)
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showRecyclerList() {
        binding.rvMovies.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvMovies.adapter = adapter
    }
}