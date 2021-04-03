package com.caiqueluz.kryptos.data.dto

import com.google.gson.annotations.SerializedName

class CurrencyQuoteDTO(
    @SerializedName("USD")
    val priceInUsd: CurrencyUsdPriceDTO
)
