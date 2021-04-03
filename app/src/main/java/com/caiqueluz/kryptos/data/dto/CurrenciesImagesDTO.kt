package com.caiqueluz.kryptos.data.dto

import com.google.gson.annotations.SerializedName

class CurrenciesImagesDTO(
    @SerializedName("data")
    val currenciesImages: Map<String, CurrencyImageItemDTO>
)
