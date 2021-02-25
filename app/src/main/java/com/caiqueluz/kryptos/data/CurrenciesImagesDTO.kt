package com.caiqueluz.kryptos.data

import com.google.gson.annotations.SerializedName

class CurrenciesImagesDTO(
    @SerializedName("data")
    val currenciesImages: Map<String, CurrencyImageItemDTO>
)
