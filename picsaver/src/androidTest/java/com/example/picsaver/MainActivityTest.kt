package com.example.picsaver

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule


class MainActivityTest : TestCase() {

    @Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    @Before
    @Throws(Exception::class)
    override fun setUp() {
    }


    fun testSearch() {
        onView(withId(R.id.inputText)).perform(typeText("Tower"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textView2)).check(matches(withText("Результаты по запросу: Tower")));
    }

    fun testTo2act() {}
}