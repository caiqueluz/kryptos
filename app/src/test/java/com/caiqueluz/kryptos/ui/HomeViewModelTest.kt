package com.caiqueluz.kryptos.ui

import androidx.lifecycle.Observer
import com.caiqueluz.kryptos.ConcurrentTest
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import com.caiqueluz.kryptos.ui.vo.HomeItemVO
import com.nhaarman.mockitokotlin2.check
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest : ConcurrentTest() {

    private val fakeFactoryResponse = listOf(HomeItemVO("Mock", mock()))
    private val mockFactory = mock<HomeItemFactory> {
        on { create() } doReturn fakeFactoryResponse
    }

    private val viewModel = HomeViewModel(mockFactory)

    @Test
    fun whenCreateHomeItemsIsCalled_verifyResponseIsCorrect() {
        val observer = mock<Observer<List<HomeItemVO>>>()
        viewModel.items.observeForever(observer)

        viewModel.createHomeItems()

        verify(observer).onChanged(
            check { response ->
                assertTrue(response == fakeFactoryResponse)
            }
        )
    }
}
