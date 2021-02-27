package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.data.domain.CurrencyQuoteDTO
import com.caiqueluz.kryptos.data.domain.CurrencyUsdPriceDTO
import com.caiqueluz.kryptos.ui.converter.CurrencyQuoteConverter
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
        numberFormatter = spyNumberFormatter
    )

    private val fakeCurrencyQuoteDTO = CurrencyQuoteDTO(
        priceInUsd = CurrencyUsdPriceDTO(
            price = 1234.56
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
}
