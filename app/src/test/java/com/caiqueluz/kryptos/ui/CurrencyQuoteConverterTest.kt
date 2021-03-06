package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.data.domain.CurrencyQuoteDTO
import com.caiqueluz.kryptos.data.domain.CurrencyUsdPriceDTO
import com.caiqueluz.kryptos.ui.converter.CurrencyQuoteConverter
import com.caiqueluz.kryptos.ui.converter.DateConverter
import com.caiqueluz.kryptos.ui.converter.DateFormatFactory
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.NumberFormat
import java.util.*

@RunWith(JUnit4::class)
class CurrencyQuoteConverterTest {

    private val spyNumberFormatter = spy(
        NumberFormat.getCurrencyInstance(Locale.US)
    )

    private val converter = CurrencyQuoteConverter(
        numberFormatter = spyNumberFormatter,
        dateConverter = DateConverter(
            formatFactory = DateFormatFactory()
        )
    )

    private val fakeCurrencyQuoteDTO = CurrencyQuoteDTO(
        priceInUsd = CurrencyUsdPriceDTO(
            price = 1234.56,
            lastUpdatedDate = "2021-03-06T20:29:02.000Z"
        )
    )

    @Test
    fun whenConvertQuoteIsCalled_verifyNumberFormatterIsCalledWithCorrectPrice() {
        converter.convertQuote(fakeCurrencyQuoteDTO)

        verify(spyNumberFormatter).format(eq(1234.56))
    }

    @Test
    fun whenConvertQuoteIsCalled_verifyResponseHasCorrectFormattedPrice() {
        val expected = "U$1,234.56"
        val actual = converter.convertQuote(fakeCurrencyQuoteDTO).priceInUsd.price

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertQuoteIsCalled_verifyResponseHasCorrectFormattedDate() {
        val expected = "08:29, em 06 de Março"
        val actual = converter.convertQuote(fakeCurrencyQuoteDTO).priceInUsd.lastUpdatedDate

        assertEquals(expected, actual)
    }
}
