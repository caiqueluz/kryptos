package com.caiqueluz.kryptos.data

import com.google.gson.annotations.SerializedName

class CurrenciesInformationResponseDTO(
    @SerializedName("data")
    val currencies: List<CurrencyItemDTO>
)
