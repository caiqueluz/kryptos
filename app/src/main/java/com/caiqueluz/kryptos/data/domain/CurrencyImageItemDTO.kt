package com.caiqueluz.kryptos.data.domain

import com.google.gson.annotations.SerializedName

class CurrencyImageItemDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("logo")
    val imageUrl: String
)
