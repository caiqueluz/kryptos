package com.caiqueluz.kryptos.ui.converter

import com.caiqueluz.kryptos.data.domain.CurrencyQuoteDTO
import com.caiqueluz.kryptos.data.domain.CurrencyUsdPriceDTO
import com.caiqueluz.kryptos.ui.domain.CurrencyQuoteVO
import com.caiqueluz.kryptos.ui.domain.CurrencyUsdPriceVO
import java.text.NumberFormat
import javax.inject.Inject

class CurrencyQuoteConverter @Inject constructor(
    private val numberFormatter: NumberFormat
) {

    fun convertQuote(quote: CurrencyQuoteDTO): CurrencyQuoteVO =
        CurrencyQuoteVO(
            priceInUsd = quote.priceInUsd.convertPrice()
        )

    private fun CurrencyUsdPriceDTO.convertPrice(): CurrencyUsdPriceVO =
        CurrencyUsdPriceVO(
            price = formatPrice(this.price)
        )

    private fun formatPrice(price: Double): String =
        "U${numberFormatter.format(price)}"
}
