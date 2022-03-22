package com.yandihard.tvmoviesapp.submission.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.yandihard.tvmoviesapp.submission.datadummies.MoviesData
import com.yandihard.tvmoviesapp.submission.datadummies.TvshowData
import com.yandihard.tvmoviesapp.submission.source.CatalogRepository
import com.yandihard.tvmoviesapp.submission.source.local.*
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
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = MoviesData.generateDummyMovies()[0]
    private val movieId = dummyMovies.movieId.toString()
    private val dummyMovie = MoviesData.generateDummyMovie()
    private val dummyGenreMovie = MoviesData.generateDummyGenreM()
    private val dummyLanguageMovie = MoviesData.generateDummyLangM()

    private val dummyTvshows = TvshowData.generateDummyTvshows()[0]
    private val tvId = dummyTvshows.tvId.toString()
    private val dummyTv = TvshowData.generateDummyTv()
    private val dummyGenreTv = TvshowData.generateDummyGenreTv()
    private val dummyNetTv = TvshowData.generateDummyNetTv()
    private val dummyLangTv = TvshowData.generateDummyLangTv()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var movieObserver: Observer<DetailMovieEntity>

    @Mock
    private lateinit var genreMovieObserver: Observer<List<GenreEntity>>

    @Mock
    private lateinit var languageMovieObserver: Observer<List<LanguageEntity>>

    @Mock
    private lateinit var tvObserver: Observer<DetailTvEntity>

    @Mock
    private lateinit var genreTvObserver: Observer<List<GenreTvEntity>>

    @Mock
    private lateinit var netTvObserver: Observer<List<NetworkEntity>>

    @Mock
    private lateinit var langTvObserver: Observer<List<LanguageTvEntity>>

    @Before
    fun setup() {
        viewModel = DetailViewModel(catalogRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvshow(tvId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<DetailMovieEntity>()
        movie.value = dummyMovie

        `when`(catalogRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie().value as DetailMovieEntity
        verify(catalogRepository).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.name, movieEntity.name)
        assertEquals(dummyMovie.release, movieEntity.release)
        assertEquals(dummyMovie.status, movieEntity.status)
        assertEquals(dummyMovie.score, movieEntity.score)
        assertEquals(dummyMovie.budget, movieEntity.budget)
        assertEquals(dummyMovie.revenue, movieEntity.revenue)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.image, movieEntity.image)

        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getGenreMovie() {
        val genre = MutableLiveData<List<GenreEntity>>()
        genre.value = dummyGenreMovie

        `when`(catalogRepository.getGenreMovie(movieId)).thenReturn(genre)
        val genreEntities = viewModel.getGenreMovie().value
        verify(catalogRepository).getGenreMovie(movieId)
        assertNotNull(genreEntities)
        assertEquals(10, genreEntities?.size)

        viewModel.getGenreMovie().observeForever(genreMovieObserver)
        verify(genreMovieObserver).onChanged(dummyGenreMovie)
    }

    @Test
    fun getLangMovie() {
        val language = MutableLiveData<List<LanguageEntity>>()
        language.value = dummyLanguageMovie

        `when`(catalogRepository.getLangMovie(movieId)).thenReturn(language)
        val langEntities = viewModel.getLangMovie().value
        verify(catalogRepository).getLangMovie(movieId)
        assertNotNull(langEntities)
        assertEquals(10, langEntities?.size)

        viewModel.getLangMovie().observeForever(languageMovieObserver)
        verify(languageMovieObserver).onChanged(dummyLanguageMovie)
    }

    @Test
    fun getTv() {
        val tvshow = MutableLiveData<DetailTvEntity>()
        tvshow.value = dummyTv

        `when`(catalogRepository.getTvShow(tvId)).thenReturn(tvshow)
        val tvEntity = viewModel.getDetailTv().value as DetailTvEntity
        verify(catalogRepository).getTvShow(tvId)
        assertNotNull(tvEntity)
        assertEquals(dummyTv.name, tvEntity.name)
        assertEquals(dummyTv.release, tvEntity.release)
        assertEquals(dummyTv.status, tvEntity.status)
        assertEquals(dummyTv.score, tvEntity.score)
        assertEquals(dummyTv.type, tvEntity.type)
        assertEquals(dummyTv.overview, tvEntity.overview)
        assertEquals(dummyTv.tvId, tvEntity.tvId)
        assertEquals(dummyTv.image, tvEntity.image)

        viewModel.getDetailTv().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTv)
    }

    @Test
    fun getGenreTv() {
        val genre = MutableLiveData<List<GenreTvEntity>>()
        genre.value = dummyGenreTv

        `when`(catalogRepository.getGenreTv(tvId)).thenReturn(genre)
        val genreEntities = viewModel.getGenreTv().value
        verify(catalogRepository).getGenreTv(tvId)
        assertNotNull(genreEntities)
        assertEquals(10, genreEntities?.size)

        viewModel.getGenreTv().observeForever(genreTvObserver)
        verify(genreTvObserver).onChanged(dummyGenreTv)
    }

    @Test
    fun getNetTv() {
        val network = MutableLiveData<List<NetworkEntity>>()
        network.value = dummyNetTv

        `when`(catalogRepository.getNetTv(tvId)).thenReturn(network)
        val netEntities = viewModel.getNetTv().value
        verify(catalogRepository).getNetTv(tvId)
        assertNotNull(netEntities)
        assertEquals(10, netEntities?.size)

        viewModel.getNetTv().observeForever(netTvObserver)
        verify(netTvObserver).onChanged(dummyNetTv)
    }

    @Test
    fun getLangTv() {
        val language = MutableLiveData<List<LanguageTvEntity>>()
        language.value = dummyLangTv

        `when`(catalogRepository.getLangTv(tvId)).thenReturn(language)
        val langEntities = viewModel.getLangTv().value
        verify(catalogRepository).getLangTv(tvId)
        assertNotNull(langEntities)
        assertEquals(10, langEntities?.size)

        viewModel.getLangTv().observeForever(langTvObserver)
        verify(langTvObserver).onChanged(dummyLangTv)
    }
}