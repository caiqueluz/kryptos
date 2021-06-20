package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.ui.converter.CurrenciesIdsConverter
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrenciesIdsConverterTest {

    private val converter = CurrenciesIdsConverter()

    @Test
    fun whenConvertIdsIsCalled_verifyResponseIsCorrect() {
        val expected = "1,2,3"
        val actual = converter.convertIds(fakeCurrenciesListingDTO)

        assertEquals(expected, actual)
    }
}
