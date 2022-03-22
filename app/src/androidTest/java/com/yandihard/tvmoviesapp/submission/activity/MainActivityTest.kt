package com.yandihard.tvmoviesapp.submission.activity

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.yandihard.tvmoviesapp.R
import com.yandihard.tvmoviesapp.submission.datadummies.MoviesData
import com.yandihard.tvmoviesapp.submission.datadummies.TvshowData
import com.yandihard.tvmoviesapp.submission.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummiesMovies = MoviesData.generateDummyMovies()
    private val dummiesTvshows = TvshowData.generateDummyTvshows()
    private lateinit var instrumentalContext: Context

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup(){
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadListMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummiesMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvDetailMovieName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailMovieName)).check(matches(withText(dummiesMovies[0].name)))
        onView(withId(R.id.tvDetailMovieOver)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailMovieOver)).check(matches(withText(dummiesMovies[0].overview)))
        onView(withId(R.id.imgDetailMovie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadListTvshows() {
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rvTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummiesTvshows.size))
    }

    @Test
    fun loadDetailTvshow() {
        onView(withId(R.id.view_pager)).perform(swipeLeft(), swipeLeft())
        onView(withId(R.id.rvTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imgDetailTv)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTvName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTvName)).check(matches(withText(dummiesTvshows[0].name)))
        onView(withId(R.id.tvDetailTvOver)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTvOver)).check(matches(withText(dummiesTvshows[0].overview)))
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.fav)).check(matches(isDisplayed()))
        onView(withId(R.id.fav)).perform(click())
        onView(withId(R.id.rvFavMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFavMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvDetailMovieName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailMovieName)).check(matches(withText(dummiesMovies[0].name)))
        onView(withId(R.id.tvDetailMovieOver)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailMovieOver)).check(matches(withText(dummiesMovies[0].overview)))
        onView(withId(R.id.imgDetailMovie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteTv() {
        onView(withId(R.id.view_pager)).perform(swipeLeft(), swipeLeft())
        onView(withId(R.id.rvTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.fav)).check(matches(isDisplayed()))
        onView(withId(R.id.fav)).perform(click())
        onView(withId(R.id.viewPagerFav)).perform(swipeLeft(), swipeLeft())
        onView(withId(R.id.rvFavTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFavTvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imgDetailTv)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTvName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTvName)).check(matches(withText(dummiesTvshows[0].name)))
        onView(withId(R.id.tvDetailTvOver)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTvOver)).check(matches(withText(dummiesTvshows[0].overview)))
    }
}