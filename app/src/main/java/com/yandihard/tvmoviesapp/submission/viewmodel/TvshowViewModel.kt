package com.yandihard.tvmoviesapp.submission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yandihard.tvmoviesapp.submission.source.CatalogRepository
import com.yandihard.tvmoviesapp.submission.source.local.TvEntity
import com.yandihard.tvmoviesapp.submission.vo.Resource

class TvshowViewModel(private val tvRepository: CatalogRepository) : ViewModel() {

    fun getTvshows(): LiveData<Resource<PagedList<TvEntity>>> = tvRepository.getListTvshows()
}