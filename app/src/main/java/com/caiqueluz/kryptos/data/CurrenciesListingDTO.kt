package com.caiqueluz.kryptos.data

import com.google.gson.annotations.SerializedName

class CurrenciesListingDTO(
    @SerializedName("data")
    val data: List<CurrenciesListingItemDTO>
)
