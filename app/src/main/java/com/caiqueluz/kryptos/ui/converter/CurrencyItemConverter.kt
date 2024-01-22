package com.caiqueluz.kryptos.ui.converter

import com.caiqueluz.kryptos.data.dto.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.ui.vo.CurrencyItemVO
import com.caiqueluz.kryptos.ui.vo.CurrencyQuoteVO

class CurrencyItemConverter {

    fun convertCurrencyItem(
        listingItem: CurrenciesListingItemDTO,
        quote: CurrencyQuoteVO,
        imageUrl: String
    ): CurrencyItemVO = CurrencyItemVO(
        name = listingItem.name,
        symbol = listingItem.symbol,
        imageUrl = imageUrl,
        quote = quote
    )
}
