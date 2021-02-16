package com.caiqueluz.kryptos.ui.viewmodel

import com.caiqueluz.kryptos.data.CurrenciesDTO
import com.caiqueluz.kryptos.data.CurrencyItemDTO

class CurrenciesConverter {

    fun convert(dto: CurrenciesDTO): CurrenciesVO =
        CurrenciesVO(
            currencies = convertCurrencyList(dto.currencies)
        )

    private fun convertCurrencyList(
        list: List<CurrencyItemDTO>
    ): List<CurrencyItemVO> = list.map { item ->
        CurrencyItemVO(
            name = item.name,
            symbol = item.symbol,
            category = item.category,
            logoUrl = item.logoUrl,
            description = item.description
        )
    }
}
