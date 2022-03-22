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
import com.yandihard.tvmoviesapp.databinding.FragmentTvshowBinding
import com.yandihard.tvmoviesapp.submission.adapter.TvshowsAdapter
import com.yandihard.tvmoviesapp.submission.viewmodel.TvshowViewModel
import com.yandihard.tvmoviesapp.submission.viewmodel.ViewModelFactory
import com.yandihard.tvmoviesapp.submission.vo.Status

class TvshowFragment : Fragment() {
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TvshowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TvshowsAdapter(requireActivity())
        showRecyclerList()
        binding.rvTvshow.setHasFixedSize(true)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[TvshowViewModel::class.java]
        viewModel.getTvshows()

        viewModel.getTvshows().observe(this, { listTvshows ->
            if (listTvshows != null) {
                when (listTvshows.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(listTvshows.data)
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
        binding.rvTvshow.layoutManager = LinearLayoutManager(activity)
        binding.rvTvshow.adapter = adapter
    }
}