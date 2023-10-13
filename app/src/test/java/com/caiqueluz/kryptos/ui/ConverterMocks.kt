package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingItemDTO
import org.mockito.kotlin.mock

val fakeCurrenciesListingDTO = CurrenciesListingDTO(
    currencies = listOf(
        createFakeListingItemDTO(id = 1),
        createFakeListingItemDTO(id = 2),
        createFakeListingItemDTO(id = 3)
    )
)

fun createFakeListingItemDTO(id: Int) = CurrenciesListingItemDTO(
    id = id, name = String(), symbol = String(), quote = mock()
)
