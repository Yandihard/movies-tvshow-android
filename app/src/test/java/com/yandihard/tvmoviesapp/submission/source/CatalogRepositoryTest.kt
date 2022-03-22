package com.yandihard.tvmoviesapp.submission.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.yandihard.tvmoviesapp.submission.datadummies.MoviesData
import com.yandihard.tvmoviesapp.submission.datadummies.TvshowData
import com.yandihard.tvmoviesapp.submission.source.local.LocalDataSource
import com.yandihard.tvmoviesapp.submission.source.local.MovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.TvEntity
import com.yandihard.tvmoviesapp.submission.source.remote.RemoteDataSource
import com.yandihard.tvmoviesapp.submission.utils.AppExecutors
import com.yandihard.tvmoviesapp.submission.utils.LiveDataTestUtil
import com.yandihard.tvmoviesapp.submission.utils.PagedListUtil
import com.yandihard.tvmoviesapp.submission.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = mock(LocalDataSource::class.java)
    private val remote = mock(RemoteDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local, appExecutors)

    private val moviesResponses = MoviesData.generateRemoteDummyMovies()
    private val tvshowsResponses = TvshowData.generateRemoteDummyTvshows()
    private val movieResponses = MoviesData.generateRemoteDummyMovie()
    private val movieId = movieResponses.id.toString()
    private val genreMovieResponses = MoviesData.generateRemoteDummyGenreM()
    private val langMovieResponses = MoviesData.generateRemoteDummyLangM()
    private val tvResponses = TvshowData.generateRemoteDummyTv()
    private val tvId = tvResponses.id.toString()
    private val genreTvResponses = TvshowData.generateRemoteDummyGenreTv()
    private val netTvResponses = TvshowData.generateRemoteDummyNetTv()
    private val langTvResponses = TvshowData.generateRemoteDummyLangTv()

    @Test
    fun getListMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getListMovie()).thenReturn(dataSourceFactory)
        catalogRepository.getListMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(MoviesData.generateDummyMovies()))
        verify(local).getListMovie()
        assertNotNull(movieEntities.data)
        assertEquals(moviesResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getListTvshows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getListTv()).thenReturn(dataSourceFactory)
        catalogRepository.getListTvshows()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(TvshowData.generateDummyTvshows()))
        verify(local).getListTv()
        assertNotNull(tvEntities.data)
        assertEquals(tvshowsResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieCallback)
                    .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getMovie(eq(movieId), any())

        val movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovie(movieId))

        verify(remote).getMovie(eq(movieId), any())

        assertNotNull(movieEntities)
        assertEquals(movieResponses.title, movieEntities.name)
        assertEquals(movieResponses.revenue, movieEntities.revenue)
        assertEquals(movieResponses.id, movieEntities.movieId)
        assertEquals(movieResponses.budget, movieEntities.budget)
        assertEquals(movieResponses.overview, movieEntities.overview)
        assertEquals(movieResponses.posterPath, movieEntities.image)
        assertEquals(movieResponses.releaseDate, movieEntities.release)
        assertEquals(movieResponses.voteAverage, movieEntities.score)
        assertEquals(movieResponses.status, movieEntities.status)
    }

    @Test
    fun getGenreMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadGenreMovieCallback)
                    .onAllGenreReceived(genreMovieResponses)
            null
        }.`when`(remote).getGenreMovie(eq(movieId), any())
        val genreEntities = LiveDataTestUtil.getValue(catalogRepository.getGenreMovie(movieId))
        verify(remote).getGenreMovie(eq(movieId), any())
        assertNotNull(genreEntities)
        assertNotNull(genreEntities[0].name)
        assertEquals(genreMovieResponses[0].name, genreEntities[0].name)
        assertEquals(genreMovieResponses.size.toLong(), genreEntities.size.toLong())
    }

    @Test
    fun getLangMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadLangMovieCallback)
                    .onAllLangReceived(langMovieResponses)
            null
        }.`when`(remote).getLangMovie(eq(movieId), any())
        val langEntities = LiveDataTestUtil.getValue(catalogRepository.getLangMovie(movieId))
        verify(remote).getLangMovie(eq(movieId), any())
        assertNotNull(langEntities)
        assertNotNull(langEntities[0].name)
        assertEquals(langMovieResponses[0].name, langEntities[0].name)
        assertEquals(langMovieResponses.size.toLong(), langEntities.size.toLong())
    }

    @Test
    fun getTvshow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvshowCallback)
                    .onAllTvshowReceived(tvResponses)
            null
        }.`when`(remote).getTvShow(eq(tvId), any())

        val tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTvShow(tvId))

        verify(remote).getTvShow(eq(tvId), any())

        assertNotNull(tvEntities)
        assertEquals(tvResponses.name, tvEntities.name)
        assertEquals(tvResponses.id, tvEntities.tvId)
        assertEquals(tvResponses.overview, tvEntities.overview)
        assertEquals(tvResponses.posterPath, tvEntities.image)
        assertEquals(tvResponses.firstAirDate, tvEntities.release)
        assertEquals(tvResponses.voteAverage, tvEntities.score)
        assertEquals(tvResponses.status, tvEntities.status)
    }

    @Test
    fun getGenreTv() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadGenreTvCallback)
                    .onAllGenreTvReceived(genreTvResponses)
            null
        }.`when`(remote).getGenreTv(eq(tvId), any())
        val genreEntities = LiveDataTestUtil.getValue(catalogRepository.getGenreTv(tvId))
        verify(remote).getGenreTv(eq(tvId), any())
        assertNotNull(genreEntities)
        assertNotNull(genreEntities[0].name)
        assertEquals(genreTvResponses[0].name, genreEntities[0].name)
        assertEquals(genreTvResponses.size.toLong(), genreEntities.size.toLong())
    }

    @Test
    fun getNetTv() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadNetTvCallback)
                    .onAllNetTvReceived(netTvResponses)
            null
        }.`when`(remote).getNetTv(eq(tvId), any())
        val netEntities = LiveDataTestUtil.getValue(catalogRepository.getNetTv(tvId))
        verify(remote).getNetTv(eq(tvId), any())
        assertNotNull(netEntities)
        assertNotNull(netEntities[0].name)
        assertEquals(netTvResponses[0].name, netEntities[0].name)
        assertEquals(netTvResponses.size.toLong(), netEntities.size.toLong())
    }

    @Test
    fun getLangTv() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadLangTvCallback)
                    .onAllLangTvReceived(langTvResponses)
            null
        }.`when`(remote).getLangTv(eq(tvId), any())
        val langEntities = LiveDataTestUtil.getValue(catalogRepository.getLangTv(tvId))
        verify(remote).getLangTv(eq(tvId), any())
        assertNotNull(langEntities)
        assertNotNull(langEntities[0].name)
        assertEquals(langTvResponses[0].name, langEntities[0].name)
        assertEquals(langTvResponses.size.toLong(), langEntities.size.toLong())
    }
}