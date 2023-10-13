package com.caiqueluz.kryptos.utils

import android.graphics.Bitmap
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class PicassoImageLoader(
    private val dispatcher: CoroutineDispatcher,
    private val picasso: Picasso
) : ImageLoader {

    override fun loadImage(url: String) = runBlocking<Bitmap?> {
        val deferredBitmap = async(dispatcher) {
            picasso.load(url).get()
        }

        return@runBlocking deferredBitmap.await()
    }
}
