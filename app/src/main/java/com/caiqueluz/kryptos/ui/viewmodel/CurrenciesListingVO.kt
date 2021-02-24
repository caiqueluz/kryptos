package com.caiqueluz.kryptos.ui.viewmodel

class CurrenciesListingVO(
    val currencies: List<CurrenciesListingItemVO>
)

fun List<CurrenciesListingItemVO>.getUrls(): String =
    this.map { it.id.toString() }
        .joinToString { it }
        .filterNot { it.isWhitespace() }
