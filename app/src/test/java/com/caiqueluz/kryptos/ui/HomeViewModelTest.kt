package com.caiqueluz.kryptos.ui

import androidx.lifecycle.Observer
import com.caiqueluz.kryptos.ConcurrentTest
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import com.caiqueluz.kryptos.ui.vo.HomeItemVO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.check
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest : ConcurrentTest() {

    private val fakeFactoryResponse = listOf(HomeItemVO("Mock", mock()))
    private val mockFactory = mock<HomeItemFactory> {
        on { create() } doReturn fakeFactoryResponse
    }

    private val viewModel = HomeViewModel(mockFactory)

    @Test
    fun whenOnScreenStartedIsCalled_verifyResponseIsCorrect() {
        val observer = mock<Observer<List<HomeItemVO>>>()
        viewModel.items.observeForever(observer)

        viewModel.onScreenStarted()

        verify(observer).onChanged(
            check { response ->
                assertTrue(response == fakeFactoryResponse)
            }
        )
    }
}
