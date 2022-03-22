package com.yandihard.tvmoviesapp.submission.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandihard.tvmoviesapp.databinding.FragmentFavoriteTvBinding
import com.yandihard.tvmoviesapp.submission.adapter.FavoriteTvAdapter
import com.yandihard.tvmoviesapp.submission.viewmodel.FavoriteViewModel
import com.yandihard.tvmoviesapp.submission.viewmodel.ViewModelFactory

class FavoriteTvFragment : Fragment() {
    private var _binding: FragmentFavoriteTvBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteTvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteTvAdapter(requireActivity())
        showRecyclerList()
        binding.rvFavTvshow.setHasFixedSize(true)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getFavTv().observe(viewLifecycleOwner, { listTv ->
            binding.progressBar.visibility = View.GONE
            if (listTv != null) {
                adapter.submitList(listTv)
            }
        })
    }

    private fun showRecyclerList() {
        binding.rvFavTvshow.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFavTvshow.adapter = adapter
    }
}