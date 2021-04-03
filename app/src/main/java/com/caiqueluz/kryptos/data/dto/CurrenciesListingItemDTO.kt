package com.caiqueluz.kryptos.data.domain

import com.google.gson.annotations.SerializedName

class CurrenciesListingItemDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("quote")
    val quote: CurrencyQuoteDTO
)
