package com.caiqueluz.kryptos.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrencyRepositoryTest {

    private val mockApi = mock<CurrencyApi>()

    private val repository = CurrencyRepository(
        api = mockApi
    )

    @Test
    fun whenFetchCurrenciesInformationIsCalled_verifyApiIsCalled() {
        repository.fetchCurrenciesInformation()

        verify(mockApi).fetchCurrenciesInformation()
    }
}
