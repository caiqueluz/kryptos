package com.caiqueluz.kryptos.ui.vo

import android.graphics.Bitmap

data class CurrencyDetailDialogVO(
    val image: Bitmap?,
    val name: String,
    val symbol: String,
    val lastUpdatedDate: String?
)
