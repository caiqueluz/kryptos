package com.caiqueluz.kryptos.data.domain

import com.google.gson.annotations.SerializedName

class CurrencyImageItemDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val imageUrl: String
)
