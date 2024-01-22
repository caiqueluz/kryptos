package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrencyImageItemDTO
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyItemConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyQuoteConverter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(JUnit4::class)
class CurrenciesConverterTest {

    private val mockQuoteConverter = mock<CurrencyQuoteConverter> {
        on { convertQuote(any()) } doReturn mock()
    }

    private val mockItemConverter = mock<CurrencyItemConverter> {
        on { convertCurrencyItem(any(), any(), any()) } doReturn mock()
    }

    private val converter = CurrenciesConverter(
        quoteConverter = mockQuoteConverter,
        itemConverter = mockItemConverter
    )

    private val fakeCurrenciesImagesDTO = CurrenciesImagesDTO(
        currenciesImages = mapOf(
            "1" to CurrencyImageItemDTO(id = 1, String()),
            "2" to CurrencyImageItemDTO(id = 2, String()),
            "3" to CurrencyImageItemDTO(id = 3, String())
        )
    )

    @Test
    fun whenConvertCurrenciesIsCalled_verifyResponseSizeIsCorrect() {
        val expected = 3

        val actual = converter.convertCurrencies(
            fakeCurrenciesListingDTO, fakeCurrenciesImagesDTO
        ).currencies.size

        assertEquals(expected, actual)
    }
}
