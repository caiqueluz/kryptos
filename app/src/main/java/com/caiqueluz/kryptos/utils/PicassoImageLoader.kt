package com.caiqueluz.kryptos.utils

import android.graphics.Bitmap
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PicassoImageLoader @Inject constructor(
    private val picasso: Picasso
) : ImageLoader {

    override fun loadImage(url: String): Bitmap = runBlocking {
        val deferredBitmap = async(Dispatchers.IO) {
            picasso.load(url).get()
        }

        return@runBlocking deferredBitmap.await()
    }
}
