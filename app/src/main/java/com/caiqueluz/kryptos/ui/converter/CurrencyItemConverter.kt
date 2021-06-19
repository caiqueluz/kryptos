package com.caiqueluz.kryptos.ui.converter

import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.data.dto.CurrencyImageItemDTO
import com.caiqueluz.kryptos.ui.vo.CurrencyItemVO
import com.caiqueluz.kryptos.utils.ImageLoader

class CurrencyItemConverter(
    private val imageLoader: ImageLoader,
    private val quoteConverter: CurrencyQuoteConverter
) {

    fun convertCurrencyItems(
        images: Map<String, CurrencyImageItemDTO>,
        listing: CurrenciesListingDTO
    ): List<CurrencyItemVO> = images.map { (_, dto) ->
        val listingItem = getListingItem(listing, dto)
        val quoteDTO = listingItem.quote
        val quote = quoteConverter.convertQuote(quoteDTO)

        return@map CurrencyItemVO(
            name = listingItem.name,
            symbol = listingItem.symbol,
            image = imageLoader.loadImage(dto.imageUrl),
            quote = quote
        )
    }

    private fun getListingItem(
        listing: CurrenciesListingDTO,
        imageItem: CurrencyImageItemDTO
    ): CurrenciesListingItemDTO = listing.currencies.first { listingItem ->
        listingItem.id == imageItem.id
    }
}
