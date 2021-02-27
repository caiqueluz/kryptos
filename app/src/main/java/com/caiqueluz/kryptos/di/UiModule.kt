package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyQuoteConverter
import com.caiqueluz.kryptos.utils.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.NumberFormat
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    fun provideNumberFormatter(): NumberFormat =
        NumberFormat.getCurrencyInstance(Locale.US)

    @Provides
    fun provideQuoteConverter(
        numberFormatter: NumberFormat
    ): CurrencyQuoteConverter = CurrencyQuoteConverter(numberFormatter)

    @Provides
    fun provideCurrenciesConverter(
        imageLoader: ImageLoader,
        quoteConverter: CurrencyQuoteConverter
    ): CurrenciesConverter = CurrenciesConverter(
        imageLoader, quoteConverter
    )
}
