package com.caiqueluz.kryptos.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caiqueluz.kryptos.testutils.CoroutinesTestRule
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.News
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.Currencies
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.Settings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

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
    fun whenStarted_verifyItemsOrderIsCorrect() = runTest {
        val expected =
            listOf(
                Currencies,
                News,
                Settings
            )

        val actual = viewModel.items.value

        assertEquals(expected, actual)
    }
}
