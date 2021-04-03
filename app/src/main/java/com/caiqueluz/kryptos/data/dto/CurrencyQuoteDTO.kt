package com.caiqueluz.kryptos.data.domain

import com.google.gson.annotations.SerializedName

class CurrencyQuoteDTO(
    @SerializedName("USD")
    val priceInUsd: CurrencyUsdPriceDTO
)
