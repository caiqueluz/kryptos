package com.caiqueluz.kryptos.data

import com.google.gson.annotations.SerializedName

class CurrenciesListingItemDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)
