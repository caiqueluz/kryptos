package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.utils.IODispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object ThreadHandlingModule {

    @IODispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher =
        Dispatchers.IO
}
