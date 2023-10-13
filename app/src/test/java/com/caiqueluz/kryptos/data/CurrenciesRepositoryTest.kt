package com.caiqueluz.kryptos.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrenciesRepositoryTest {

    private val mockApi = mock<CurrenciesApi>()
    private val repository = CurrenciesRepository(mockApi)

    @Test
    fun whenFetchCurrenciesListingIsCalled_verifyApiIsCalled() = runTest {
        repository.fetchCurrenciesListing(limit = 10)

        verify(mockApi).fetchCurrenciesListing(eq(10))
    }

    @Test
    fun whenFetchCurrenciesWithImagesIsCalled_verifyApiIsCalled() = runTest {
        repository.fetchCurrenciesWithImages("1,2,3")

        verify(mockApi).fetchCurrenciesWithImages(eq("1,2,3"))
    }
}
