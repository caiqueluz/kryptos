package com.caiqueluz.kryptos.data

import com.google.gson.annotations.SerializedName

class CurrencyInformationResponseDTO(
    @SerializedName("data")
    val currencies: List<CurrencyItemDTO>
)
