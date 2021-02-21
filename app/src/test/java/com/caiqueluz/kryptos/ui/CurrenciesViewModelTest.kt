package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.*
import com.caiqueluz.kryptos.data.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesConverter
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesListingVO
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesVO
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrenciesViewModelTest : CoroutinesTest() {

    private val mockRepository = mock<CurrenciesRepository>()

    private val mockConverter = mock<CurrenciesConverter> {
        on { convertCurrenciesListing(any()) } doReturn mock()
        on { convertCurrenciesImages(any()) } doReturn mock()
    }

    private val viewModel = CurrenciesViewModel(
        dispatcher = testDispatcher,
        repository = mockRepository,
        converter = mockConverter
    )

    @Test
    fun whenFetchCurrenciesListingIsCalled_verifyLoadingResponse() = runBlockingTest {
        val response = successResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesListingVO>()
        viewModel.currenciesListing.observeForever(observer)

        viewModel.fetchCurrenciesListing()

        verifyLoadingResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesListingIsCalled_verifyContentResponse() = runBlockingTest {
        val response = successResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesListingVO>()
        viewModel.currenciesListing.observeForever(observer)

        viewModel.fetchCurrenciesListing()

        verifyContentResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesIsCalled_verifyErrorResponse() = runBlockingTest {
        val response = errorResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesListingVO>()
        viewModel.currenciesListing.observeForever(observer)

        viewModel.fetchCurrenciesListing()

        verifyErrorResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesImagesIsCalled_verifyLoadingResponse() = runBlockingTest {
        val response = successResponse<CurrenciesImagesDTO>()
        whenever(mockRepository.fetchCurrenciesImages(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currenciesImages.observeForever(observer)

        viewModel.fetchCurrenciesImages(String())

        verifyLoadingResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesImagesIsCalled_verifyContentResponse() = runBlockingTest {
        val response = successResponse<CurrenciesImagesDTO>()
        whenever(mockRepository.fetchCurrenciesImages(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currenciesImages.observeForever(observer)

        viewModel.fetchCurrenciesImages(String())

        verifyContentResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesImagesIsCalled_verifyErrorResponse() = runBlockingTest {
        val response = errorResponse<CurrenciesImagesDTO>()
        whenever(mockRepository.fetchCurrenciesImages(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currenciesImages.observeForever(observer)
        viewModel.fetchCurrenciesImages(String())

        verifyErrorResponse(observer)
    }
}
