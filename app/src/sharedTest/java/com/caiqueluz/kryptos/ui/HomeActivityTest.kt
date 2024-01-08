package com.caiqueluz.kryptos.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caiqueluz.kryptos.R
import com.caiqueluz.kryptos.TestKryptosApplication
import com.caiqueluz.kryptos.ui.view.HomeActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestKryptosApplication::class)
class HomeActivityTest {

    @Before
    fun before() {
        launch()
    }

    @Test
    fun whenStarted_verifyTabIsDisplayed() {
        onView(withId(R.id.home_tab_container)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_verifyTabHasCorrectSize() {
        onView(withId(R.id.home_tab_container)).check(
            matches(hasChildCount(3))
        )
    }

    @Test
    fun whenStarted_verifyCurrenciesTabItemIsDisplayed() {
        onView(
            allOf(
                isDescendantOfA(withId(R.id.home_tab_container)),
                withText("MockTab")
            )
        ).check(
            matches(isDisplayed())
        )
    }

    private fun launch() = ActivityScenario.launch(HomeActivity::class.java)
}
