package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.*
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.nhaarman.mockitokotlin2.*
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
        on { convertCurrencies(any(), any()) } doReturn mock()
        on { convertIds(anyOrNull()) } doReturn String()
    }

    private val listingResponse = successResponse<CurrenciesListingDTO>()
    private val imagesResponse = successResponse<CurrenciesImagesDTO>()

    private val viewModel = CurrenciesViewModel(
        dispatcher = testDispatcher,
        repository = mockRepository,
        converter = mockConverter
    )

    @Test
    fun whenFetchCurrenciesIsCalled_verifyLoadingResponse() = runBlockingTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        verifyLoadingResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesIsCalled_verifyContentResponse() = runBlockingTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        verifyContentResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesIsCalled_verifyErrorResponse() = runBlockingTest {
        val response = errorResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        verifyErrorResponse(observer)
    }
}
