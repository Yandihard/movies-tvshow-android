package com.yandihard.tvmoviesapp.submission.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandihard.tvmoviesapp.databinding.FragmentFavoriteMoviesBinding
import com.yandihard.tvmoviesapp.submission.adapter.FavoriteMovieAdapter
import com.yandihard.tvmoviesapp.submission.viewmodel.FavoriteViewModel
import com.yandihard.tvmoviesapp.submission.viewmodel.ViewModelFactory

class FavoriteMoviesFragment : Fragment() {
    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteMovieAdapter(requireActivity())
        showRecyclerList()
        binding.rvFavMovies.setHasFixedSize(true)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getFavMovie().observe(viewLifecycleOwner, { listMovies ->
            binding.progressBar.visibility = View.GONE
            if (listMovies != null) {
                adapter.submitList(listMovies)
            }
        })
    }

    private fun showRecyclerList() {
        binding.rvFavMovies.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFavMovies.adapter = adapter
    }
}