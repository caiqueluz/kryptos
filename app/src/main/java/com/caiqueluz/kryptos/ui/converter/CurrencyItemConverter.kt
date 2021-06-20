package com.caiqueluz.kryptos.ui.converter

import com.caiqueluz.kryptos.data.dto.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.ui.vo.CurrencyItemVO
import com.caiqueluz.kryptos.ui.vo.CurrencyQuoteVO
import com.caiqueluz.kryptos.utils.ImageLoader

class CurrencyItemConverter(
    private val imageLoader: ImageLoader
) {

    fun convertCurrencyItem(
        listingItem: CurrenciesListingItemDTO,
        quote: CurrencyQuoteVO,
        imageUrl: String
    ): CurrencyItemVO = CurrencyItemVO(
        name = listingItem.name,
        symbol = listingItem.symbol,
        image = imageLoader.loadImage(imageUrl),
        quote = quote
    )
}
