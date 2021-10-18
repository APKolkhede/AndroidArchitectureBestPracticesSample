package com.techtest.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class HostActivityTest {
    @get:Rule
    val rule = activityScenarioRule<HostActivity>()

    @Test
    fun clickButtonHome() {
        onView(withId(R.id.home_navigation)).perform(click()).check(matches(isDisplayed()))
    }

    @Test
    fun clickButtonUpcomingMovies() {
        onView(withId(R.id.upcoming_movies_navigation)).perform(click())
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickButtonFavoriteMovies() {
        onView(withId(R.id.favorite_nav_graph)).perform(click()).check(matches(isDisplayed()))
    }
}
