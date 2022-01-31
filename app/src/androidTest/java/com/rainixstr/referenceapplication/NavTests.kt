package com.rainixstr.referenceapplication

import android.content.pm.ActivityInfo
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class NavTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun openAboutActivity() {
        launchActivity<MainActivity>()
        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))

        pressBack()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.fragment1)).check(doesNotExist())
        Espresso.onView(withId(R.id.fragment2)).check(doesNotExist())
        Espresso.onView(withId(R.id.fragment3)).check(doesNotExist())
        Espresso.onView(withId(R.id.bnToFirst)).check(doesNotExist())
        Espresso.onView(withId(R.id.bnToSecond)).check(doesNotExist())
        Espresso.onView(withId(R.id.bnToThird)).check(doesNotExist())

    }

    @Test
    fun clickOnButton() {
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBack()

        Espresso.onView(withId(R.id.bnToFirst)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))

        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBack()

        Espresso.onView(withId(R.id.bnToFirst)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBack()

        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToFirst)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        repeat(5) {
            Espresso.onView(withId(R.id.bnToSecond)).perform(click())
            Espresso.onView(withId(R.id.bnToThird)).perform(click())
        }
        Espresso.onView(withId(R.id.bnToFirst)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        pressBackUnconditionally()
        Assert.assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun clickOnButtonAndRotate() {
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBack()

        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        repeat(3) {
            Espresso.onView(withId(R.id.bnToFirst)).perform(click())
            Espresso.onView(withId(R.id.bnToSecond)).perform(click())
            Espresso.onView(withId(R.id.bnToThird)).perform(click())
        }
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToFirst)).perform(click())
    }

    @Test
    fun checkBackStack() {
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        pressBack()
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBack()

        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        pressBack()
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        repeat(5) {
            Espresso.onView(withId(R.id.bnToSecond)).perform(click())
            Espresso.onView(withId(R.id.bnToThird)).perform(click())
        }
        pressBack()
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun checkBackStackWithRotation() {
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        pressBack()
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        pressBack()
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        repeat(5) {
            Espresso.onView(withId(R.id.bnToSecond)).perform(click())
            Espresso.onView(withId(R.id.bnToThird)).perform(click())
        }

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        pressBack()
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

}