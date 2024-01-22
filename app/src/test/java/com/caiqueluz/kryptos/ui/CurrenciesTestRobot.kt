package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals

class CurrenciesTestRobot(
    private val viewModel: CurrenciesViewModel
) {

    private val networkResponses = mutableListOf<NetworkResponse<CurrenciesVO>>()

    fun start(): CurrenciesTestRobot {
        viewModel.onScreenStarted()

        return this
    }

    fun clickOnErrorModalTryAgainButton(): CurrenciesTestRobot {
        viewModel.onErrorModalTryAgainButtonClicked()

        return this
    }

    fun expectLoading() {
        val expected = NetworkResponse.Loading
        val actual = networkResponses.first()

        assertEquals(expected, actual)
    }

    fun expectContent(content: CurrenciesVO) {
        val expected = NetworkResponse.Content(content)
        val actual = networkResponses[1]

        assertEquals(expected, actual)
    }

    fun expectError() {
        assertTrue(networkResponses[1] is NetworkResponse.Error)
    }
}
