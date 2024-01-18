package com.caiqueluz.kryptos.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.caiqueluz.kryptos.CoroutinesTestRule
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import com.caiqueluz.kryptos.ui.vo.HomeItemVO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.check
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private val factory = HomeItemFactory()
    private val viewModel = HomeViewModel(factory)

    @Test
    fun whenOnScreenStartedIsCalled_verifyResponseIsCorrect() {
        val observer = mock<Observer<List<HomeItemVO>>>()
        viewModel.items.observeForever(observer)

        viewModel.onScreenStarted()

        verify(observer).onChanged(
            check { response ->
                assertTrue(response == factory.create())
            }
        )
    }
}
