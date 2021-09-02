package com.jaime.marvelviewer

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class SeriesTests {

    @Rule @JvmField var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testViewsAreVisible() {
        onView(withId(R.id.progressBarLoadingSeries)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.swipeToRefreshSeries)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recyclerViewSeriesItems)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}