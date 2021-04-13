package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.utils.ImageLoader
import com.caiqueluz.kryptos.utils.PicassoImageLoader
import com.squareup.picasso.Picasso
import org.koin.core.qualifier.named
import org.koin.dsl.module

val utilsModule = module {
    single {
        Picasso.get()
    }

    single<ImageLoader> {
        PicassoImageLoader(get(qualifier = named(IO_DISPATCHER)), picasso = get())
    }
}
