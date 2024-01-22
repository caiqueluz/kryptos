package com.caiqueluz.kryptos.ui.vo

data class CurrencyItemVO(
    val name: String,
    val symbol: String,
    val imageUrl: String? = null,
    val quote: CurrencyQuoteVO
)
