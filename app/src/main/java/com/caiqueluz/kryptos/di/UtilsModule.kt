package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.utils.ImageLoader
import com.caiqueluz.kryptos.utils.PicassoImageLoader
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val IO_DISPATCHER = "IO_DISPATCHER"

val utilsModule = module {
    single(named(IO_DISPATCHER)) {
        Dispatchers.IO
    }

    single {
        Picasso.get()
    }

    single<ImageLoader> {
        PicassoImageLoader(
            dispatcher = get(qualifier = named(IO_DISPATCHER)),
            picasso = get()
        )
    }
}
