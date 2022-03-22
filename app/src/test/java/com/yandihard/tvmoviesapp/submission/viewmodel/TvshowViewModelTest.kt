package com.yandihard.tvmoviesapp.submission.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.yandihard.tvmoviesapp.submission.source.CatalogRepository
import com.yandihard.tvmoviesapp.submission.source.local.TvEntity
import com.yandihard.tvmoviesapp.submission.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvshowViewModelTest {

    private lateinit var viewModel: TvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setup() {
        viewModel = TvshowViewModel(tvRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvshows = Resource.success(pagedList)
        `when`(dummyTvshows.data?.size).thenReturn(5)
        val tvshows = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tvshows.value = dummyTvshows

        `when`(tvRepository.getListTvshows()).thenReturn(tvshows)
        val tvEntities = viewModel.getTvshows().value?.data
        verify(tvRepository).getListTvshows()
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getTvshows().observeForever(observer)
        verify(observer).onChanged(dummyTvshows)
    }
}