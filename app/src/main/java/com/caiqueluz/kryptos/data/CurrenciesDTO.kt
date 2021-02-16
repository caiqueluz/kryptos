package com.caiqueluz.kryptos.data

import com.google.gson.annotations.SerializedName

class CurrenciesDTO(
    @SerializedName("data")
    val currencies: List<CurrencyItemDTO>
)
