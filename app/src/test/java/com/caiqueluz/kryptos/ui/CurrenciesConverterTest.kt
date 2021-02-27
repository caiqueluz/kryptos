package com.caiqueluz.kryptos.ui

import android.graphics.Bitmap
import com.caiqueluz.kryptos.data.domain.*
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyQuoteConverter
import com.caiqueluz.kryptos.utils.ImageLoader
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.NumberFormat
import java.util.*

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

    private val fakeImagesResponse = CurrenciesImagesDTO(
        currenciesImages = mapOf(
            "1" to fakeImageItemDTO(),
            "2" to fakeImageItemDTO(),
            "3" to fakeImageItemDTO(),
            "4" to fakeImageItemDTO(),
            "5" to fakeImageItemDTO()
        )
    )

    private val spyQuoteConverter = CurrencyQuoteConverter(
        numberFormatter = NumberFormat.getCurrencyInstance(Locale.US)
    )

    private val converter = CurrenciesConverter(
        imageLoader = mockImageLoader,
        quoteConverter = spyQuoteConverter
    )

    @Test
    fun whenConvertIdsIsCalled_verifyResponseIsCorrect() {
        val expected = "5,5,5,5,5"
        val actual = converter.convertIds(fakeListingResponse)

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesIsCalled_verifyResponseHasCorrectName() {
        val expected = "name"
        val actual =
            converter.convertCurrencies(
                fakeListingResponse,
                fakeImagesResponse
            ).currencies[0].name

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesIsCalled_verifyResponseHasCorrectSymbol() {
        val expected = "symbol"
        val actual =
            converter.convertCurrencies(
                fakeListingResponse,
                fakeImagesResponse
            ).currencies[0].symbol

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesIsCalled_verifyResponseHasCorrectImage() {
        val expected = mockBitmap
        val actual =
            converter.convertCurrencies(
                fakeListingResponse,
                fakeImagesResponse
            ).currencies[0].image

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesIsCalled_verifyResponseHasCorrectPrice() {
        val expected = "U$1,234.56"
        val actual =
            converter.convertCurrencies(
                fakeListingResponse,
                fakeImagesResponse
            ).currencies[0].quote.priceInUsd.price

        assertEquals(expected, actual)
    }

    private fun fakeListingDTO() = CurrenciesListingItemDTO(
        id = 5,
        name = "name",
        symbol = "symbol",
        quote = CurrencyQuoteDTO(
            priceInUsd = CurrencyUsdPriceDTO(
                price = 1234.56
            )
        )
    )

    private fun fakeImageItemDTO() = CurrencyImageItemDTO(
        id = 5,
        imageUrl = "imageurl.com"
    )
}
