package com.caiqueluz.kryptos.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caiqueluz.kryptos.CoroutinesTestRule
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.errorResponse
import com.caiqueluz.kryptos.successResponse
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrenciesIdsConverter
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
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

    private val mockCurrencies = mock<CurrenciesVO>()

    private val mockCurrenciesConverter = mock<CurrenciesConverter> {
        on { convertCurrencies(any(), any()) } doReturn mockCurrencies
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

        robot()
            .start()
            .expectLoading()
    }

    @Test
    fun whenOnScreenStartedIsCalled_verifyContentResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        robot()
            .start()
            .expectContent(content = mockCurrencies)
    }

    @Test
    fun whenOnScreenStartedIsCalled_verifyErrorResponse() = runTest {
        val response = errorResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        robot()
            .start()
            .expectError()
    }

    @Test
    fun whenOnErrorModalTryAgainButtonClickedIsCalled_verifyLoadingResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        robot()
            .start()
            .clickOnErrorModalTryAgainButton()
            .expectLoading()
    }

    @Test
    fun whenOnErrorModalTryAgainButtonClickedIsCalled_verifyContentResponse() = runTest {
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(listingResponse)
        whenever(mockRepository.fetchCurrenciesWithImages(any())).thenReturn(imagesResponse)

        robot()
            .start()
            .clickOnErrorModalTryAgainButton()
            .expectContent(content = mockCurrencies)
    }

    @Test
    fun whenOnErrorModalTryAgainButtonClickedCalled_verifyErrorResponse() = runTest {
        val response = errorResponse<CurrenciesListingDTO>()
        whenever(mockRepository.fetchCurrenciesListing(any())).thenReturn(response)

        robot()
            .start()
            .clickOnErrorModalTryAgainButton()
            .expectError()
    }

    private fun robot() = CurrenciesTestRobot(viewModel = viewModel)
}
