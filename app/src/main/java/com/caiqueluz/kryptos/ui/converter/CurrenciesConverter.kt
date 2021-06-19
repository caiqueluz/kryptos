package com.caiqueluz.kryptos.ui.converter

import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO

class CurrenciesConverter(
    private val itemConverter: CurrencyItemConverter
) {

    fun convertIds(listing: CurrenciesListingDTO): String =
        listing.currencies.map { it.id.toString() }
            .joinToString { it }
            .filterNot { it.isWhitespace() }

    fun convertCurrencies(
        listing: CurrenciesListingDTO, images: CurrenciesImagesDTO
    ): CurrenciesVO = CurrenciesVO(
        currencies = itemConverter.convertCurrencyItems(images.currenciesImages, listing)
    )
}
