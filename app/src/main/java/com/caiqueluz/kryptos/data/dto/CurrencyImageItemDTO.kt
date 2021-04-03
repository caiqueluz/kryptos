package com.caiqueluz.kryptos.data.dto

import com.google.gson.annotations.SerializedName

class CurrencyImageItemDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val imageUrl: String
)
