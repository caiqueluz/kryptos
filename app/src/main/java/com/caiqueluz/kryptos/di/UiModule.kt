package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    fun provideCurrenciesConverter(): CurrenciesConverter =
        CurrenciesConverter()
}
