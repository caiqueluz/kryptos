package com.caiqueluz.kryptos.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caiqueluz.kryptos.R
import com.caiqueluz.kryptos.TestKryptosApplication
import com.caiqueluz.kryptos.injectTestModule
import com.caiqueluz.kryptos.launchFragment
import com.caiqueluz.kryptos.ui.view.CurrenciesFragment
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import com.caiqueluz.kryptos.ui.vo.CurrencyItemVO
import com.caiqueluz.kryptos.ui.vo.CurrencyQuoteVO
import com.caiqueluz.kryptos.ui.vo.CurrencyUsdPriceVO
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(application = TestKryptosApplication::class)
class CurrenciesFragmentTest {

    private val mockViewModel = mock<CurrenciesViewModel>()

    private val mockCurrencies = CurrenciesVO(
        currencies = listOf(
            CurrencyItemVO(
                name = "Criptomoeda ABC",
                symbol = "ABC",
                image = mock(),
                quote = CurrencyQuoteVO(
                    priceInUsd = CurrencyUsdPriceVO(
                        price = "U$ 1.234,56"
                    )
                )
            )
        )
    )

    @Before
    fun before() {
        setupTestModule()
    }

    @Test
    fun whenStarted_verifyScreenTitleIsDisplayed() {
        whenever(mockViewModel.currencies).mockLoading()

        launchFragment<CurrenciesFragment>()

        onView(withId(R.id.currencies_screen_title)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_verifyScreenTitleHasCorrectText() {
        whenever(mockViewModel.currencies).mockLoading()

        launchFragment<CurrenciesFragment>()

        onView(withText("Criptomoedas")).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_withLoading_verifyLoadingIsDisplayed() {
        whenever(mockViewModel.currencies).mockLoading()

        launchFragment<CurrenciesFragment>()

        onView(withId(R.id.currencies_loading_progressbar)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_withContent_verifyCurrencyImageIsDisplayed() {
        whenever(mockViewModel.currencies).mockContent(mockCurrencies)

        launchFragment<CurrenciesFragment>()

        onView(withId(R.id.currency_item_image)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_withContent_verifyCurrencyNameIsDisplayed() {
        whenever(mockViewModel.currencies).mockContent(mockCurrencies)

        launchFragment<CurrenciesFragment>()

        onView(withText("Criptomoeda ABC")).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_withContent_verifyCurrencySymbolIsDisplayed() {
        whenever(mockViewModel.currencies).mockContent(mockCurrencies)

        launchFragment<CurrenciesFragment>()

        onView(withText("ABC")).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_withContent_verifyCurrencyPriceIsDisplayed() {
        whenever(mockViewModel.currencies).mockContent(mockCurrencies)

        launchFragment<CurrenciesFragment>()

        onView(withText("U$ 1.234,56")).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_withContent_verifyCurrencyInformationButtonIsDisplayed() {
        whenever(mockViewModel.currencies).mockContent(mockCurrencies)

        launchFragment<CurrenciesFragment>()

        onView(withId(R.id.currency_item_price_information)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_withContent_verifyContentIsDisplayed() {
        whenever(mockViewModel.currencies).mockContent(mockCurrencies)

        launchFragment<CurrenciesFragment>()

        onView(withId(R.id.currencies_recycler_view)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun whenStarted_withError_verifyErrorIsDisplayed() {
        whenever(mockViewModel.currencies).mockError()

        launchFragment<CurrenciesFragment>()

        onView(withText("Erro ao carregar informações."))
            .inRoot(isDialog()).check(
                matches(isDisplayed())
            )
    }

    private fun setupTestModule() {
        injectTestModule {
            single { mockViewModel }
        }
    }
}
