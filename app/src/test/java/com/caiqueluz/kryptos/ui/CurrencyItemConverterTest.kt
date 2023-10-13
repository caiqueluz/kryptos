package com.caiqueluz.kryptos.ui

import android.graphics.Bitmap
import com.caiqueluz.kryptos.data.dto.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.ui.converter.CurrencyItemConverter
import com.caiqueluz.kryptos.ui.vo.CurrencyQuoteVO
import com.caiqueluz.kryptos.utils.ImageLoader
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrencyItemConverterTest {

    private val mockImage = mock<Bitmap>()

    private val mockImageLoader = mock<ImageLoader> {
        on { loadImage(any()) } doReturn mockImage
    }

    private val converter = CurrencyItemConverter(imageLoader = mockImageLoader)

    private val mockQuoteVO = mock<CurrencyQuoteVO>()
    private val mockImageUrl = "mockurl.com"

    private val fakeListingItemDTO = CurrenciesListingItemDTO(
        id = 1, name = "MockName", symbol = "MOCK", quote = mock()
    )

    @Test
    fun whenConvertCurrencyItemIsCalled_verifyResponseNameIsCorrect() {
        val expected = "MockName"
        val actual = converter.convertCurrencyItem(
            fakeListingItemDTO, mockQuoteVO, mockImageUrl
        ).name

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrencyItemIsCalled_verifyResponseSymbolIsCorrect() {
        val expected = "MOCK"
        val actual = converter.convertCurrencyItem(
            fakeListingItemDTO, mockQuoteVO, mockImageUrl
        ).symbol

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrencyItemIsCalled_verifyResponseImageIsCorrect() {
        val expected = mockImage
        val actual = converter.convertCurrencyItem(
            fakeListingItemDTO, mockQuoteVO, mockImageUrl
        ).image

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrencyItemIsCalled_verifyResponseQuoteIsCorrect() {
        val expected = mockQuoteVO
        val actual = converter.convertCurrencyItem(
            fakeListingItemDTO, mockQuoteVO, mockImageUrl
        ).quote

        assertEquals(expected, actual)
    }
}
