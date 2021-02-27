/*package com.caiqueluz.kryptos.ui

import android.graphics.Bitmap
import com.caiqueluz.kryptos.data.domain.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.domain.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.domain.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.data.domain.CurrencyImageItemDTO
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.domain.CurrenciesListingItemVO
import com.caiqueluz.kryptos.ui.domain.CurrenciesListingVO
import com.caiqueluz.kryptos.utils.ImageLoader
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrenciesConverterTest {

    private val mockBitmap = mock<Bitmap>()

    private val mockImageLoader = mock<ImageLoader> {
        on { loadImage(any()) } doReturn mockBitmap
    }

    private val fakeListingResponse = CurrenciesListingDTO(
        currencies = listOf(
            fakeListingDTO(),
            fakeListingDTO(),
            fakeListingDTO(),
            fakeListingDTO(),
            fakeListingDTO()
        )
    )

    private val fakeVOListingResponse = CurrenciesListingVO(
        currencies = listOf(
            fakeListingVO(),
            fakeListingVO(),
            fakeListingVO(),
            fakeListingVO(),
            fakeListingVO()
        )
    )

    private val fakeImagesResponse = CurrenciesImagesDTO(
        currenciesImages = mapOf(
            "1" to fakeImageItemDTO(),
            "2" to fakeImageItemDTO(),
            "3" to fakeImageItemDTO(),
            "4" to fakeImageItemDTO(),
            "5" to fakeImageItemDTO()
        )
    )

    private val converter = CurrenciesConverter(
        imageLoader = mockImageLoader
    )

    @Test
    fun whenConvertCurrenciesListingIsCalled_verifyResponseHasCorrectSize() {
        val expected = 5
        val actual =
            converter.convertCurrenciesListing(fakeListingResponse).currencies.size

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesListingIsCalled_verifyResponseItemHasCorrectId() {
        val expected = 5
        val actual =
            converter.convertCurrenciesListing(fakeListingResponse).currencies[0].id

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesListingIsCalled_verifyResponseItemHasCorrectName() {
        val expected = "name"
        val actual =
            converter.convertCurrenciesListing(fakeListingResponse).currencies[0].name

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesListingIsCalled_verifyResponseItemHasCorrectSymbol() {
        val expected = "symbol"
        val actual =
            converter.convertCurrenciesListing(fakeListingResponse).currencies[0].symbol

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertIdsIsCalled_verifyResponseIsCorrect() {
        val expected = "5,5,5,5,5"
        val actual = converter.convertIds(fakeVOListingResponse)

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesImagesIsCalled_verifyResponseHasCorrectImage() {
        val expected = mockBitmap
        val actual =
            converter.convertCurrenciesImages(fakeImagesResponse).currencies[0].image

        assertEquals(expected, actual)
    }

    private fun fakeListingDTO() = CurrenciesListingItemDTO(
        id = 5,
        name = "name",
        symbol = "symbol"
    )

    private fun fakeListingVO() = CurrenciesListingItemVO(
        id = 5,
        name = "name",
        symbol = "symbol"
    )

    private fun fakeImageItemDTO() = CurrencyImageItemDTO(
        name = "name",
        symbol = "symbol",
        imageUrl = "imageurl.com"
    )
}
