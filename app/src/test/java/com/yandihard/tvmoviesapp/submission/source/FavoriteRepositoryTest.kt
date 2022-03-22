package com.yandihard.tvmoviesapp.submission.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.yandihard.tvmoviesapp.submission.datadummies.MoviesData
import com.yandihard.tvmoviesapp.submission.datadummies.TvshowData
import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.DetailTvEntity
import com.yandihard.tvmoviesapp.submission.source.local.LocalDataSource
import com.yandihard.tvmoviesapp.submission.utils.PagedListUtil
import com.yandihard.tvmoviesapp.submission.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FavoriteRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = Mockito.mock(LocalDataSource::class.java)
    private val favoriteRepository = FakeFavoriteRepository(local)

    private val moviesResponses = MoviesData.generateRemoteDummyMovies()
    private val tvshowsResponses = TvshowData.generateRemoteDummyTvshows()

    @Test
    fun getFavMovie() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailMovieEntity>
        Mockito.`when`(local.getFavMovie()).thenReturn(dataSourceFactory)
        favoriteRepository.getFavMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(MoviesData.generateDummyMovies()))
        verify(local).getFavMovie()
        assertNotNull(movieEntities.data)
        assertEquals(moviesResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavTv() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailTvEntity>
        Mockito.`when`(local.getFavTv()).thenReturn(dataSourceFactory)
        favoriteRepository.getFavTv()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(TvshowData.generateDummyTvshows()))
        verify(local).getFavTv()
        assertNotNull(tvEntities.data)
        assertEquals(tvshowsResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }
}