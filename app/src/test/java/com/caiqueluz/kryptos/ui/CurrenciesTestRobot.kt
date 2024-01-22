package com.caiqueluz.kryptos.ui

import app.cash.turbine.TurbineContext
import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import kotlinx.coroutines.CoroutineScope
import org.junit.Assert.assertTrue

class CurrenciesTestRobot(
    turbineContext: TurbineContext,
    private val backgroundScope: CoroutineScope,
    private val viewModel: CurrenciesViewModel
) {

    private val turbine = with(turbineContext) {
        viewModel.currencies.testIn(backgroundScope)
    }

    fun start(): CurrenciesTestRobot {
        viewModel.onScreenStarted()

        return this
    }

    fun clickOnErrorModalTryAgainButton(): CurrenciesTestRobot {
        viewModel.onErrorModalTryAgainButtonClicked()

        return this
    }

    suspend fun expectInOrder(vararg responses: NetworkResponse<CurrenciesVO>) {
        responses.forEach { response ->
            val actual = turbine.awaitItem()
            assertTrue(actual == response)
        }
    }
}
