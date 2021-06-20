package com.caiqueluz.kryptos.ui.converter

import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.data.dto.CurrencyImageItemDTO
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO

class CurrenciesConverter(
    private val quoteConverter: CurrencyQuoteConverter,
    private val itemConverter: CurrencyItemConverter
) {

    fun convertCurrencies(
        listing: CurrenciesListingDTO, images: CurrenciesImagesDTO
    ): CurrenciesVO {
        val items = images.currenciesImages.map { (_, dto) ->
            val listingItem = getListingItem(listing, dto)
            val quoteDTO = listingItem.quote
            val quote = quoteConverter.convertQuote(quoteDTO)

            return@map itemConverter.convertCurrencyItem(
                listingItem, quote, dto.imageUrl
            )
        }

        return CurrenciesVO(items)
    }

    private fun getListingItem(
        listing: CurrenciesListingDTO,
        imageItem: CurrencyImageItemDTO
    ): CurrenciesListingItemDTO = listing.currencies.first { listingItem ->
        listingItem.id == imageItem.id
    }
}
