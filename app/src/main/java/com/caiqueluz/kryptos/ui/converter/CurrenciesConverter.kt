package com.caiqueluz.kryptos.ui.viewmodel

import com.caiqueluz.kryptos.data.*
import com.caiqueluz.kryptos.utils.ImageLoader
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class CurrenciesConverter @Inject constructor(
    private val imageLoader: ImageLoader
) {

    fun convertCurrenciesListing(listing: CurrenciesListingDTO): CurrenciesListingVO =
        CurrenciesListingVO(
            currencies = listing.currencies.convertListing()
        )

    fun convertIds(listing: CurrenciesListingDTO): String =
        listing.currencies.map { it.id.toString() }
            .joinToString { it }
            .filterNot { it.isWhitespace() }

    fun convertCurrenciesImages(
        listing: CurrenciesListingDTO, images: CurrenciesImagesDTO
    ): CurrenciesVO =
        CurrenciesVO(
            currencies = images.currenciesImages.convertImages(listing)
        )

    private fun List<CurrenciesListingItemDTO>.convertListing(): List<CurrenciesListingItemVO> =
        this.map { dto ->
            CurrenciesListingItemVO(
                id = dto.id,
                name = dto.name,
                symbol = dto.symbol,
                quote = dto.quote.convertQuote()
            )
        }

    private fun CurrencyQuoteDTO.convertQuote(): CurrencyQuoteVO =
        CurrencyQuoteVO(
            priceInUsd = this.priceInUsd.convertPrice()
        )

    private fun CurrencyUsdPriceDTO.convertPrice(): CurrencyUsdPriceVO =
        CurrencyUsdPriceVO(
            price = formatPrice(this.price)
        )

    private fun formatPrice(price: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        return "U${formatter.format(price)}"
    }

    private fun Map<String, CurrencyImageItemDTO>.convertImages(
        listing: CurrenciesListingDTO
    ): List<CurrencyItemVO> =
        this.map { (_, dto) ->
            CurrencyItemVO(
                name = dto.name,
                symbol = dto.symbol,
                image = imageLoader.loadImage(dto.imageUrl),
                quote = listing.getListingItem(dto).quote.convertQuote()
            )
        }

    private fun CurrenciesListingDTO.getListingItem(
        imageItem: CurrencyImageItemDTO
    ): CurrenciesListingItemDTO = this.currencies.first { listingItem ->
        listingItem.name == imageItem.name
    }
}
