package com.caiqueluz.kryptos.data

import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrenciesRepositoryTest {

    private val mockApi = mock<CurrenciesApi>()
    private val repository = CurrenciesRepository(mockApi)

    @Test
    fun whenFetchCurrenciesListingIsCalled_verifyApiIsCalled() = runBlockingTest {
        repository.fetchCurrenciesListing(limit = 10)

        verify(mockApi).fetchCurrenciesListing(eq(10))
    }

    @Test
    fun whenFetchCurrenciesWithImagesIsCalled_verifyApiIsCalled() = runBlockingTest {
        repository.fetchCurrenciesWithImages("1,2,3")

        verify(mockApi).fetchCurrenciesWithImages(eq("1,2,3"))
    }
}
