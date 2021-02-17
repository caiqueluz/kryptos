package com.caiqueluz.kryptos.utils

import android.graphics.Bitmap

interface ImageLoader {

    fun loadImage(url: String): Bitmap
}
