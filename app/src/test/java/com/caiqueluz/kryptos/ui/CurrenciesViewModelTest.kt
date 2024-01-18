package com.caiqueluz.kryptos.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caiqueluz.kryptos.CoroutinesTestRule
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.errorResponse
import com.caiqueluz.kryptos.networkResponseObserver
import com.caiqueluz.kryptos.successResponse
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrenciesIdsConverter
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import com.caiqueluz.kryptos.verifyContentResponse
import com.caiqueluz.kryptos.verifyErrorResponse
import com.caiqueluz.kryptos.verifyLoadingResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
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
class CurrenciesViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

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
        dispatcher = coroutinesRule.dispatcher,
        repository = mockRepository,
        idsConverter = mockIdsConverter,
        currenciesConverter = mockCurrenciesConverter
    )

    @Test
    fun whenOnScreenStartedIsCalled_verifyLoadingResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.onScreenStarted()

        verifyLoadingResponse(observer)
    }

    @Test
    fun whenOnScreenStartedIsCalled_verifyContentResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.onScreenStarted()

        verifyContentResponse(observer)
    }

    @Test
    fun whenOnScreenStartedIsCalled_verifyErrorResponse() = runTest {
        val response = errorResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.onScreenStarted()

        verifyErrorResponse(observer)
    }

    @Test
    fun whenOnErrorModalTryAgainButtonClickedIsCalled_verifyLoadingResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.onErrorModalTryAgainButtonClicked()

        verifyLoadingResponse(observer)
    }

    @Test
    fun whenOnErrorModalTryAgainButtonClickedIsCalled_verifyContentResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.onErrorModalTryAgainButtonClicked()

        verifyContentResponse(observer)
    }

    @Test
    fun whenOnErrorModalTryAgainButtonClickedCalled_verifyErrorResponse() = runTest {
        val response = errorResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        val observer = networkResponseObserver<CurrenciesVO>()
        viewModel.currencies.observeForever(observer)

        viewModel.onErrorModalTryAgainButtonClicked()

        verifyErrorResponse(observer)
    }
}
