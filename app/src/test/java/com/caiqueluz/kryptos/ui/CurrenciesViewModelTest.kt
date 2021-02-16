package com.caiqueluz.kryptos.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.caiqueluz.kryptos.data.CurrenciesDTO
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesConverter
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesVO
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Call
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrenciesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockRepository = mock<CurrenciesRepository>()
    private val mockConverter = mock<CurrenciesConverter> {
        on { convert(any()) } doReturn mock()
    }

    private val mockResponse = mock<Response<CurrenciesDTO>> {
        on { isSuccessful } doReturn true
        on { body() } doReturn mock()
    }

    private val mockCall = mock<Call<CurrenciesDTO>> {
        on { execute() } doReturn mockResponse
    }

    private val viewModel = CurrenciesViewModel(
        repository = mockRepository,
        converter = mockConverter,
    )

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher = TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenFetchCurrenciesIsCalled_verifyLoadingResponse() {
        whenever(mockRepository.fetchCurrencies()).thenReturn(mock())

        val observer = mock<Observer<NetworkResponse<CurrenciesVO>>>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        val captor = argumentCaptor<NetworkResponse<CurrenciesVO>>()
        verify(observer, times(2)).onChanged(captor.capture())

        assertTrue(captor.firstValue is NetworkResponse.Loading)
    }

    @Test
    fun whenFetchCurrenciesIsCalled_verifyContentResponse() {
        whenever(mockRepository.fetchCurrencies()).thenReturn(mockCall)

        val observer = mock<Observer<NetworkResponse<CurrenciesVO>>>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        val captor = argumentCaptor<NetworkResponse<CurrenciesVO>>()
        verify(observer, times(2)).onChanged(captor.capture())

        assertTrue(captor.lastValue is NetworkResponse.Content)
    }

    @Test
    fun whenFetchCurrenciesIsCalled_verifyErrorResponse() {
        whenever(mockRepository.fetchCurrencies()).then {
            throw Exception()
        }

        val observer = mock<Observer<NetworkResponse<CurrenciesVO>>>()
        viewModel.currencies.observeForever(observer)

        viewModel.fetchCurrencies()

        val captor = argumentCaptor<NetworkResponse<CurrenciesVO>>()
        verify(observer, times(2)).onChanged(captor.capture())

        assertTrue(captor.lastValue is NetworkResponse.Error)
    }
}
