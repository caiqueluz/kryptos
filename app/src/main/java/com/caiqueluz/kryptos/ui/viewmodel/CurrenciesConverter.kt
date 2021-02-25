package com.caiqueluz.kryptos.ui.viewmodel

import com.caiqueluz.kryptos.data.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.data.CurrencyImageItemDTO
import com.caiqueluz.kryptos.utils.ImageLoader
import javax.inject.Inject

class CurrenciesConverter @Inject constructor(
    private val imageLoader: ImageLoader
) {

    fun convertCurrenciesListing(dto: CurrenciesListingDTO): CurrenciesListingVO =
        CurrenciesListingVO(
            currencies = dto.currencies.convertListing()
        )

    fun convertIds(listing: CurrenciesListingVO): String =
        listing.currencies.map { it.id.toString() }
            .joinToString { it }
            .filterNot { it.isWhitespace() }

    fun convertCurrenciesImages(dto: CurrenciesImagesDTO): CurrenciesVO =
        CurrenciesVO(
            currencies = dto.currenciesImages.convertImages()
        )

    private fun List<CurrenciesListingItemDTO>.convertListing(): List<CurrenciesListingItemVO> =
        this.map { dto ->
            CurrenciesListingItemVO(
                id = dto.id,
                name = dto.name,
                symbol = dto.symbol
            )
        }

    private fun Map<String, CurrencyImageItemDTO>.convertImages(): List<CurrencyItemVO> =
        this.map { (_, dto) ->
            CurrencyItemVO(
                name = dto.name,
                symbol = dto.symbol,
                image = imageLoader.loadImage(dto.imageUrl)
            )
        }
}
