package com.caiqueluz.kryptos.ui

import androidx.fragment.app.Fragment
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
import com.caiqueluz.kryptos.injectTestModule
import com.caiqueluz.kryptos.ui.view.HomeActivity
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import com.caiqueluz.kryptos.ui.vo.HomeItemVO
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestKryptosApplication::class)
class HomeActivityTest {

    private val mockItemFactory = mock<HomeItemFactory> {
        on { create() } doReturn listOf(
            HomeItemVO("MockTab", Fragment())
        )
    }

    private val spyViewModel = spy(HomeViewModel(factory = mockItemFactory))

    @Before
    fun before() {
        setupTestModule()
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
            matches(hasChildCount(mockItemFactory.create().size))
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

    private fun setupTestModule() {
        injectTestModule {
            single { spyViewModel }
        }
    }

    private fun launch() = ActivityScenario.launch(HomeActivity::class.java)
}
