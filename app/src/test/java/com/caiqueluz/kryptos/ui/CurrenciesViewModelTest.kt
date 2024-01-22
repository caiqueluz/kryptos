package com.caiqueluz.kryptos.ui

import app.cash.turbine.TurbineContext
import com.caiqueluz.kryptos.testutils.CoroutinesTestRule
import com.caiqueluz.kryptos.testutils.MockWebServerRule
import com.caiqueluz.kryptos.testutils.ServerResponseFaker
import com.caiqueluz.kryptos.testutils.baseKoinTestModule
import com.caiqueluz.kryptos.di.appModules
import com.caiqueluz.kryptos.testutils.fakeErrorListingResponse
import com.caiqueluz.kryptos.testutils.fakeSuccessListingResponse
import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.testutils.runTurbineTest
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import com.caiqueluz.kryptos.ui.vo.CurrencyItemVO
import com.caiqueluz.kryptos.ui.vo.CurrencyQuoteVO
import com.caiqueluz.kryptos.ui.vo.CurrencyUsdPriceVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTestRule

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrenciesViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @get:Rule
    val koinRule = KoinTestRule.create {
        modules(appModules + listOf(baseKoinTestModule))
    }

    private val serverFaker = ServerResponseFaker()

    @get:Rule
    val serverRule = MockWebServerRule(faker = serverFaker)

    @Test
    fun givenSuccess_whenOnScreenStartedIsCalled_thenResponseOrderIsLoadingContent() = runTurbineTest { turbineContext ->
        serverFaker.fakeSuccessListingResponse()

        val robot = turbineContext.createRobot(backgroundScope = backgroundScope)

        robot
            .start()
            .expectInOrder(
                NetworkResponse.Loading,
                NetworkResponse.Content(
                    content = CurrenciesVO(
                        currencies = listOf(
                            CurrencyItemVO(
                                name = "crypto-1",
                                symbol = "FKC",
                                imageUrl = "http://localhost:8080/fake-image-url-1.png",
                                quote = CurrencyQuoteVO(
                                    priceInUsd = CurrencyUsdPriceVO(
                                        price = "U$123.00",
                                        lastUpdatedDate = "12:30, em 01 de janeiro"
                                    )
                                )
                            ),
                            CurrencyItemVO(
                                name = "crypto-2",
                                symbol = "FKC",
                                imageUrl = "http://localhost:8080/fake-image-url-2.png",
                                quote = CurrencyQuoteVO(
                                    priceInUsd = CurrencyUsdPriceVO(
                                        price = "U$123.00",
                                        lastUpdatedDate = "12:30, em 01 de janeiro"
                                    )
                                )
                            ),
                            CurrencyItemVO(
                                name = "crypto-3",
                                symbol = "FKC",
                                imageUrl = "http://localhost:8080/fake-image-url-3.png",
                                quote = CurrencyQuoteVO(
                                    priceInUsd = CurrencyUsdPriceVO(
                                        price = "U$123.00",
                                        lastUpdatedDate = "12:30, em 01 de janeiro"
                                    )
                                )
                            )
                        )
                    )
                )
            )
    }

    @Test
    fun givenError_whenOnScreenStartedIsCalled_thenResponseOrderIsLoadingError() = runTurbineTest { turbineContext ->
        serverFaker.fakeErrorListingResponse()

        val robot = turbineContext.createRobot(backgroundScope = backgroundScope)

        robot
            .start()
            .expectInOrder(
                NetworkResponse.Loading,
                NetworkResponse.Error
            )
    }

    @Test
    fun givenSuccess_whenOnErrorModalTryAgainButtonClickedIsCalled_thenResponseOrderIsLoadingContent() = runTurbineTest { turbineContext ->
        serverFaker.fakeSuccessListingResponse()

        val robot = turbineContext.createRobot(backgroundScope = backgroundScope)

        robot
            .start()
            .expectInOrder(
                NetworkResponse.Loading,
                NetworkResponse.Content(
                    content = CurrenciesVO(
                        currencies = listOf(
                            CurrencyItemVO(
                                name = "crypto-1",
                                symbol = "FKC",
                                imageUrl = "http://localhost:8080/fake-image-url-1.png",
                                quote = CurrencyQuoteVO(
                                    priceInUsd = CurrencyUsdPriceVO(
                                        price = "U$123.00",
                                        lastUpdatedDate = "12:30, em 01 de janeiro"
                                    )
                                )
                            ),
                            CurrencyItemVO(
                                name = "crypto-2",
                                symbol = "FKC",
                                imageUrl = "http://localhost:8080/fake-image-url-2.png",
                                quote = CurrencyQuoteVO(
                                    priceInUsd = CurrencyUsdPriceVO(
                                        price = "U$123.00",
                                        lastUpdatedDate = "12:30, em 01 de janeiro"
                                    )
                                )
                            ),
                            CurrencyItemVO(
                                name = "crypto-3",
                                symbol = "FKC",
                                imageUrl = "http://localhost:8080/fake-image-url-3.png",
                                quote = CurrencyQuoteVO(
                                    priceInUsd = CurrencyUsdPriceVO(
                                        price = "U$123.00",
                                        lastUpdatedDate = "12:30, em 01 de janeiro"
                                    )
                                )
                            )
                        )
                    )
                )
            )
    }

    @Test
    fun givenError_whenOnErrorModalTryAgainButtonClickedIsCalled_thenResponseOrderIsLoadingError() = runTurbineTest { turbineContext ->
        serverFaker.fakeErrorListingResponse()

        val robot = turbineContext.createRobot(backgroundScope = backgroundScope)

        robot
            .start()
            .expectInOrder(
                NetworkResponse.Loading,
                NetworkResponse.Error
            )
    }

    private fun TurbineContext.createRobot(backgroundScope: CoroutineScope) =
        CurrenciesTestRobot(
            turbineContext = this,
            backgroundScope = backgroundScope,
            viewModel = koinRule.koin.get()
        )
}
