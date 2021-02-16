package com.caiqueluz.kryptos.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrenciesRepositoryTest {

    private val mockApi = mock<CurrenciesApi>()

    private val repository = CurrenciesRepository(
        api = mockApi
    )

    @Test
    fun whenFetchCurrenciesInformationIsCalled_verifyApiIsCalled() {
        repository.fetchCurrencies()

        verify(mockApi).fetchCurrencies()
    }
}
