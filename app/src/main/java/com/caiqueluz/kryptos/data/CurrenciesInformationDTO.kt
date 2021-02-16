package com.caiqueluz.kryptos.data

import com.google.gson.annotations.SerializedName

class CurrenciesInformationDTO(
    @SerializedName("data")
    val currencies: List<CurrencyItemDTO>
)
