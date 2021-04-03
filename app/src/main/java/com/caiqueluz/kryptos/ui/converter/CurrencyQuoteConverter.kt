package com.caiqueluz.kryptos.ui.converter

import com.caiqueluz.kryptos.data.dto.CurrencyQuoteDTO
import com.caiqueluz.kryptos.data.dto.CurrencyUsdPriceDTO
import com.caiqueluz.kryptos.ui.vo.CurrencyQuoteVO
import com.caiqueluz.kryptos.ui.vo.CurrencyUsdPriceVO
import java.text.NumberFormat
import javax.inject.Inject

private const val LAST_UPDATED_DATE_PATTERN = "hh:mm, 'em' dd 'de' MMMM"

class CurrencyQuoteConverter @Inject constructor(
    private val numberFormatter: NumberFormat,
    private val dateConverter: DateConverter
) {

    fun convertQuote(quote: CurrencyQuoteDTO): CurrencyQuoteVO =
        CurrencyQuoteVO(
            priceInUsd = quote.priceInUsd.convertPrice()
        )

    private fun CurrencyUsdPriceDTO.convertPrice(): CurrencyUsdPriceVO =
        CurrencyUsdPriceVO(
            price = formatPrice(this.price),
            lastUpdatedDate = convertDate(this.lastUpdatedDate)
        )

    private fun formatPrice(price: Double): String =
        "U${numberFormatter.format(price)}"

    private fun convertDate(value: String): String? =
        dateConverter.convert(value, LAST_UPDATED_DATE_PATTERN)
}
