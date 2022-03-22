package com.yandihard.tvmoviesapp.submission.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.yandihard.tvmoviesapp.submission.source.FavoriteRepository
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity
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
class FavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var favViewModel: FavoriteViewModel

    @Mock
    private lateinit var favoriteRepository: FavoriteRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<DetailMovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<PagedList<DetailTvEntity>>

    @Mock
    private lateinit var pagedListMovie: PagedList<DetailMovieEntity>

    @Mock
    private lateinit var pagedListTv: PagedList<DetailTvEntity>

    @Before
    fun setup() {
        favViewModel = FavoriteViewModel(favoriteRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovie = pagedListMovie
        `when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<DetailMovieEntity>>()
        movie.value = dummyMovie

        `when`(favoriteRepository.getFavMovie()).thenReturn(movie)
        val movieEntities = favViewModel.getFavMovie().value
        verify(favoriteRepository).getFavMovie()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        favViewModel.getFavMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getFavoriteTv() {
        val dummyTv = pagedListTv
        `when`(dummyTv.size).thenReturn(5)
        val tvshow = MutableLiveData<PagedList<DetailTvEntity>>()
        tvshow.value = dummyTv

        `when`(favoriteRepository.getFavTv()).thenReturn(tvshow)
        val tvEntities = favViewModel.getFavTv().value
        verify(favoriteRepository).getFavTv()
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        favViewModel.getFavTv().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTv)
    }
}