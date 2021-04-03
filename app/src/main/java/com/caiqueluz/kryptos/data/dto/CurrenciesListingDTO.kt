package com.caiqueluz.kryptos.data.domain

import com.google.gson.annotations.SerializedName

class CurrenciesListingDTO(
    @SerializedName("data")
    val currencies: List<CurrenciesListingItemDTO>
)
