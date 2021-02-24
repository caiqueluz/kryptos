package com.caiqueluz.kryptos.utils

import android.graphics.Bitmap
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import javax.inject.Inject

class PicassoImageLoader @Inject constructor(
    private val picasso: Picasso,
    @IODispatcher
    private val dispatcher: CoroutineDispatcher
) : ImageLoader {

    override fun loadImage(url: String) = runBlocking<Bitmap?> {
        val deferredBitmap = async(dispatcher) {
            picasso.load(url).get()
        }

        return@runBlocking deferredBitmap.await()
    }
}
