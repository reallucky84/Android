package bh.yoo.mvpcoin

import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.UiThreadTestRule

import bh.yoo.mvpcoin.activity.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val mockWebServer = MockWebServer()
    private val portNumber = 8080

    private val responseBody = "{ \"login\" : \"octocat\", \"followers\" : 1500 }"

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp(){
        mockWebServer.start(portNumber)
        ActivityScenario.launch(MainActivity::class.java)

    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }


    @Test
    fun test_click_getData(){

        // withId(R.id.my_view) is a ViewMatcher
        // click() is a ViewAction
        // matches(isDisplayed()) is a ViewAssertion
        onView(withId(R.id.num))
            .perform(typeText("5"))
            .perform(click())

        onView(withId(R.id.getdata)).perform(click())


    }

}
