package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.ConcurrentTest
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrenciesIdsConverter
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrenciesViewModelTest : ConcurrentTest() {

    private val mockRepository = mock<CurrenciesRepository>()

    private val mockIdsConverter = mock<CurrenciesIdsConverter> {
        on { convertIds(anyOrNull()) } doReturn String()
    }

    private val mockCurrenciesConverter = mock<CurrenciesConverter> {
        on { convertCurrencies(any(), any()) } doReturn mock()
    }

    private val listingResponse = successResponse<CurrenciesListingDTO>()
    private val imagesResponse = successResponse<CurrenciesImagesDTO>()

    private val viewModel = CurrenciesViewModel(
        dispatcher = testDispatcher,
        repository = mockRepository,
        idsConverter = mockIdsConverter,
        currenciesConverter = mockCurrenciesConverter
    )

    @Test
    fun whenFetchCurrenciesIsCalled_verifyLoadingResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        verifyLoadingResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesIsCalled_verifyContentResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        verifyContentResponse(observer)
    }

    @Test
    fun whenFetchCurrenciesIsCalled_verifyErrorResponse() = runTest {
        val response = errorResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        verifyErrorResponse(observer)
    }
}
