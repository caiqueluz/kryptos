package com.caiqueluz.kryptos.data.dto

import com.google.gson.annotations.SerializedName

class CurrencyUsdPriceDTO(
    @SerializedName("price")
    val price: Double,
    @SerializedName("last_updated")
    val lastUpdatedDate: String
)
