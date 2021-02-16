package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.data.CurrenciesInformationDTO
import com.caiqueluz.kryptos.data.CurrencyItemDTO
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesConverter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrenciesConverterTest {

    private fun fakeCurrencyListDTO(description: String? = null) = listOf(
        CurrencyItemDTO(
            id = 123,
            name = "MockItemName",
            symbol = "MOCK",
            category = "MockCategory",
            logoUrl = "mocklogourl.com",
            description = description
        )
    )

    private fun fakeResponseDTO(
        description: String? = null
    ) = CurrenciesInformationDTO(
        currencies = fakeCurrencyListDTO(description)
    )

    private val converter = CurrenciesConverter()

    @Test
    fun whenConvertIsCalled_verifyResponseHasCorrectSize() {
        val response = converter.convert(fakeResponseDTO())

        val expected = 1
        val actual = response.currencies.size

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertIsCalled_verifyCurrencyItemHasCorrectName() {
        val response = converter.convert(fakeResponseDTO())

        val expected = "MockItemName"
        val actual = response.currencies[0].name

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertIsCalled_verifyCurrencyItemHasCorrectSymbol() {
        val response = converter.convert(fakeResponseDTO())

        val expected = "MOCK"
        val actual = response.currencies[0].symbol

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertIsCalled_verifyCurrencyItemHasCorrectCategory() {
        val response = converter.convert(fakeResponseDTO())

        val expected = "MockCategory"
        val actual = response.currencies[0].category

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertIsCalled_verifyCurrencyItemHasCorrectLogoUrl() {
        val response = converter.convert(fakeResponseDTO())

        val expected = "mocklogourl.com"
        val actual = response.currencies[0].logoUrl

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertIsCalled_withCurrencyItemNotNullDescription_verifyCurrencyItemHasCorrectDescription() {
        val expected = "Mock description"

        val response = converter.convert(fakeResponseDTO(description = expected))

        val actual = response.currencies[0].description

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertIsCalled_withCurrencyItemNullDescription_verifyCurrencyItemHasNullDescription() {
        val response = converter.convert(fakeResponseDTO())

        val actual = response.currencies[0].description

        assertNull(actual)
    }
}
