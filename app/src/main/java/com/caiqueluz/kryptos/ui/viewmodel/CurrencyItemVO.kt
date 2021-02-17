package com.caiqueluz.kryptos.ui.viewmodel

import android.graphics.Bitmap

class CurrencyItemVO(
    val name: String,
    val symbol: String,
    val category: String,
    val logo: Bitmap,
    val description: String? = null
)
