package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.utils.ImageLoader
import com.caiqueluz.kryptos.utils.PicassoImageLoader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun providePicassoInstance(): Picasso = Picasso.get()

    @Provides
    fun provideImageLoader(
        picasso: Picasso
    ): ImageLoader = PicassoImageLoader(picasso)
}
