package com.caiqueluz.kryptos.data

import com.google.gson.annotations.SerializedName

class CurrencyItemDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("logoUrl")
    val logoUrl: String,
    @SerializedName("description")
    val description: String? = null
)
