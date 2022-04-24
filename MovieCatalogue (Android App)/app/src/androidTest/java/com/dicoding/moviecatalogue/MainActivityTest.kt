package com.dicoding.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.moviecatalogue.ui.main.MainActivity
import com.dicoding.moviecatalogue.utils.DataDummy
import com.dicoding.moviecatalogue.utils.EspressoIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private val dataDummyMovies = DataDummy.generateDummyMovieResponses()
    private val dataDummyTvShow = DataDummy.generateDummyTvShowResponses()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource)
    }
    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataDummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imageMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailDesc)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_menu)).perform(click())
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataDummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imageMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailDesc)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteThenUnfavorite() {
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvDetailDesc)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailDesc)).check(matches(withText(dataDummyMovies[0].overview)))
        onView(withId(R.id.tvDetailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTitle)).check(matches(withText(dataDummyMovies[0].title)))
        onView(withId(R.id.favorite_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_menu)).perform(click())
    }
}