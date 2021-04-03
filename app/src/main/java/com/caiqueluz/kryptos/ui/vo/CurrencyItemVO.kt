package com.caiqueluz.kryptos.ui.vo

import android.graphics.Bitmap

class CurrencyItemVO(
    val name: String,
    val symbol: String,
    val image: Bitmap? = null,
    val quote: CurrencyQuoteVO
)
