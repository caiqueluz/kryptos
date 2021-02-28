package com.caiqueluz.kryptos.utils

import android.graphics.Bitmap
import com.caiqueluz.kryptos.di.IODispatcher
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import javax.inject.Inject

class PicassoImageLoader @Inject constructor(
    @IODispatcher
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
